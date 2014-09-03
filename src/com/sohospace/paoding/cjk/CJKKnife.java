/*
 * zhiliang.wang code
 */
package com.sohospace.paoding.cjk;

import com.sohospace.dictionary.Dictionary;
import com.sohospace.dictionary.Hit;
import com.sohospace.paoding.CharSet;
import com.sohospace.paoding.Collector;
import com.sohospace.paoding.Knife;

/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * 
 * @since 1.0
 * 
 */
public class CJKKnife implements Knife {

	private CJKDictionaryFactory factory;

	public CJKKnife() {
	}

	public CJKKnife(CJKDictionaryFactory factory) {
		this.factory = factory;
	}

	public CJKDictionaryFactory getFactory() {
		return factory;
	}

	public void setFactory(CJKDictionaryFactory factory) {
		this.factory = factory;
	}

	public boolean assignable(CharSequence beaf, int index) {
		return CharSet.isCjkUnifiedIdeographs(beaf.charAt(index));
	}
	
	public int dissect(Collector collector, CharSequence beaf, int offset) {
		if (CharSet.isCjkUnifiedIdeographs(beaf.charAt(beaf.length() - 1))
				 && offset > 0 && beaf.length() - offset < 50){
			return -offset;
		}
		Dictionary vocabulary = factory.getVocabulary();
		// difine the word between setup and end is a dictionary
		int setup, end;
		// service for unidentifiedIndex
		int identifiedEnd = offset;
		// difine the unidentified word's index
		int unidentifiedIndex = -1;
		//check if invoke shouldAWord()
		int maxWordLength = 0;
		Hit word = null;
		for (setup = offset, end = offset; setup < beaf.length()
				&& CharSet.isCjkUnifiedIdeographs(beaf.charAt(setup)); end = ++setup) {
			for (int count = 1; end < beaf.length()
					&& CharSet.isCjkUnifiedIdeographs(beaf.charAt(end++)); count++) {
				//first circle:end=setup+1
				word = vocabulary.search(beaf, setup, count);
				if (word.isUndefined()) {
					if (unidentifiedIndex < 0 && setup >= identifiedEnd) {
						unidentifiedIndex = setup;
					}
					break;
				} else if (word.isHit()) {
					if (identifiedEnd < end) {
						identifiedEnd = end;
					}
					if (unidentifiedIndex >= 0) {
						dissectUnidentified(collector, beaf, unidentifiedIndex,
								setup - unidentifiedIndex);
						unidentifiedIndex = -1;
					}
					collector.collect(word.getWord(), setup, end);
					if (setup == offset && maxWordLength < count) {
						maxWordLength = count;
					}
					if (!(word.isUnclosed() && end < beaf.length()// this check service fir below check
					&& beaf.charAt(end) >= word.getNext().charAt(count))) {
						break;
					}
				}
			}
		}
		if (identifiedEnd != end) {
			dissectUnidentified(collector, beaf, identifiedEnd,
					end - identifiedEnd);
		}
		int len = end - offset;
		if (len > 2 && len != maxWordLength && shouldAWord(beaf, offset, end)) {
			collect(collector, beaf, offset, end);
		}
		return setup;//now:end=start
	}

	/**
	 * the word not in all dictionary
	 * @param cellector
	 * @param beaf
	 * @param offset
	 * @param count
	 */
	protected void dissectUnidentified(Collector collector, CharSequence beaf,
			int offset, int count) {
		int end = offset + count;
		Hit word = null;
		int nearEnd = end - 1;
		for (int i = offset, j; i < end;) {
			j = skipXword(beaf, i, end);
			if (j >= 0 && i != j) {
				i = j;
				continue;
			}
			j = collectNumber(collector, beaf, i, end);
			if (j >= 0 && i != j) {
				i = j;
				continue;
			}
			word = factory.getXchars().search(beaf, i, 1);
			if (word.isHit()) {
				i++;
				continue;
			}
			// header
			if (i == offset) {
				collect(collector, beaf, offset, offset + 1);
			}
			// tail word
			if (i == nearEnd) {
				if (nearEnd != offset) {
					collect(collector, beaf, nearEnd, end);
				}
			}
			// binary cut word
			else {
				collect(collector, beaf, i, i + 2);
			}
			i++;
		}
	}

	protected boolean shouldAWord(CharSequence beaf, int offset, int end) {
		if (offset > 0 && end < beaf.length()) {//confirm the previous have word and the next have word
			int prev = offset - 1;
			if (beaf.charAt(prev) == '“' && beaf.charAt(end) == '”') {
				return true;
			} else if (beaf.charAt(prev) == '‘' && beaf.charAt(end) == '’') {
				return true;
			} else if (beaf.charAt(prev) == '\'' && beaf.charAt(end) == '\'') {
				return true;
			} else if (beaf.charAt(prev) == '\"' && beaf.charAt(end) == '\"') {
				return true;
			}
		}
		return false;
	}

	private final void collect(Collector collector, CharSequence beaf,
			int offset, int end) {
		collector.collect(beaf.subSequence(offset, end).toString(), offset, end);
	}

	private final int skipXword(CharSequence beaf, int offset, int end) {
		Hit word;
		for (int k = offset + 2; k <= end; k++) {
			word = factory.getXwords().search(beaf, offset, k - offset);
			if (word.isHit()) {
				offset = k;
			}
			if (word.isUndefined() || !word.isUnclosed()) {
				break;
			}
		}
		return offset;
	}

	private final int collectNumber(Collector collector, CharSequence beaf,
			int offset, int end) {
		int number1 = -1;
		int number2 = -1;
		int cur = offset;
		int bitValue = 0;
		int maxUnit = 0;
		boolean hasDigit = false;//dismiss the word have Number but not have unit
		for (; cur <= end && (bitValue = toNumber(beaf.charAt(cur))) >= 0; cur++) {
			if (bitValue == 2
					&& (beaf.charAt(cur) == '两' || beaf.charAt(cur) == '俩' || beaf
							.charAt(cur) == '倆')) {
				if (cur != offset)
					break;
			}
			if (bitValue >= 0 && bitValue < 10) {
				hasDigit = true;
				if (number2 < 0)
					number2 = bitValue;
				else {
					number2 *= 10;
					number2 += bitValue;
				}
			} else {
				if (number2 < 0) {
					if (number1 < 0) {
						number1 = 1;
					}
					number1 *= bitValue;
				} else {
					if (number1 < 0) {
						number1 = 0;
					}
					if (bitValue >= maxUnit) {
						number1 += number2;
						number1 *= bitValue;
						maxUnit = bitValue;
					} else {
						number1 += number2 * bitValue;
					}
				}
				number2 = -1;
			}
		}
		if (!hasDigit && cur < beaf.length()
				&& !factory.getUnits().search(beaf, cur, 1).isHit()) {
			return offset;
		}
		if (number2 > 0) {
			if (number1 < 0) {
				number1 = number2;
			} else {
				number1 += number2;
			}
		}
		if (number1 >= 0) {
			collector.collect(String.valueOf(number1), offset, cur);

			//the end is able to have unit
			Hit wd;
			int i = cur + 1;
			while (i <= beaf.length()
					&& (wd = factory.getUnits().search(beaf, cur, i - cur))
							.isHit()) {
				collector.collect(String.valueOf(number1)
						+ beaf.subSequence(cur, i), offset, i);
				cur++;
				if (!wd.isUnclosed()) {
					break;
				}
				i++;
			}
		}
		return cur;
	}

	private final int toNumber(char c) {
		switch (c) {
		case '零':
		case '〇':
			return 0;
		case '一':
		case '壹':
			return 1;
		case '二':
		case '两':
		case '俩':
		case '貳':
			return 2;
		case '三':
		case '叁':
			return 3;
		case '四':
		case '肆':
			return 4;
		case '五':
		case '伍':
			return 5;
		case '六':
		case '陸':
			return 6;
		case '柒':
		case '七':
			return 7;
		case '捌':
		case '八':
			return 8;
		case '九':
		case '玖':
			return 9;
		case '十':
		case '什':
			return 10;
		case '百':
		case '佰':
			return 100;
		case '千':
		case '仟':
			return 1000;
		case '万':
		case '萬':
			return 10000;
		case '亿':
		case '億':
			return 100000000;
		default:
			return -1;
		}
	}


}
