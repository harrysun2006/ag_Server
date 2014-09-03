/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.paoding;

/**
 * {@link Beef} is detail word.
 * {@link Beef} diffirent from {@link String}:<br>
 * {@link Beef} shared char array,{@link String} clone the string array<br>
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 * 
 */
public class Beef implements CharSequence {

	/**
	 * char array
	 */
	private final char[] value;

	/**
	 * the start index of char
	 */
	private int offset;

	/**
	 * the char count from offset to end
	 */
	private int count;

	/** Cache the hash code for the string */
	private int hash; // Default to 0

	/**
	 * contruct
	 * 
	 * @param value
	 *           word array
	 * @param offset
	 *            char start index
	 * @param count
	 *            the word count from offset to end
	 */
	public Beef(char[] value, int offset, int count) {
		this.value = value;
		set(offset, count);
	}

	public void set(int offset, int count) {
        if (offset < 0) {
            throw new StringIndexOutOfBoundsException(offset);
        }
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        if (offset > value.length - count) {
            throw new StringIndexOutOfBoundsException(offset + count);
        }
		this.offset = offset;
		this.count = count;
	}

	public char[] getValue() {
		return value;
	}

	public int getCount() {
		return count;
	}

	public int getOffset() {
		return offset;
	}
	
	/**
	 * get the index position word
	 */
	public char charAt(int index) {
		if (index >= 0 && index < count) {
			char src = value[offset + index];
			if (src > 65280 && src < 65375) {
				src = (char) (src - 65248);
				value[offset + index] = src;
			}
			if (src >= 'A' && src <= 'Z') {
				src += 32;
				value[offset + index] = src;
			} else if (src == 12288) {
				src = 32;
				value[offset + index] = 32;
			}
			return src;
		}
		return (char) -1;
	}

	public int length() {
		return count;
	}

	public CharSequence subSequence(int start, int end) {
		return new Beef(value, offset + start, end - start);
	}
	
	@Override
	public String toString() {
		return new String(value, offset, count);
	}

	@Override
	public int hashCode() {
		int h = hash;
		if (h == 0) {
			int off = offset;
			char val[] = value;
			int len = count;

			for (int i = 0; i < len; i++) {
				h = 31 * h + val[off++];
			}
			hash = h;
		}
		return h;
	}

}
