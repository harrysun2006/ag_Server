package com.viewbar.addon;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.viewbar.addon.model.VBFiles;
import com.viewbar.addon.model.VBViewbar;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:Apr 6, 2007 9:52:02 AM
 * @content
 */
public class Unzip {

	protected static Properties ADDON_PROPERTIES = new Properties();
	public static BufferedReader in;
	private final static String DEF_TARGET_DIRECTORY = ADDON_PROPERTIES.getProperty("target.directory");
	private final static String DEF_ZIP_FILE = ADDON_PROPERTIES.getProperty("zip.file");
	private final static String DEF_ROOT_PATH = ADDON_PROPERTIES.getProperty("root.path");

	static {
		try {
			ContextHelper.addResource("jdbc.properties");
			ContextHelper.addResource("jdbc-vb.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void loadProps() {
		try {
			InputStream is = ClassLoader
					.getSystemResourceAsStream("addon.properties");
			ADDON_PROPERTIES.load(is);
			is.close();
		} catch (Exception e) {
		}
	}

	private static void redirectIn(String[] args) {
		InputStream is = System.in;
		try {
			if (args.length > 0) {
				is = ClassLoader.getSystemResourceAsStream(args[0]);
			}
		} catch (Exception e) {
			is = System.in;
		}
		in = new BufferedReader(new InputStreamReader(is));
	}

	public static void main(String[] args) throws Exception {
		Configuration config = null;
		SessionFactory sessionFactory;
		Session session = null;
		Transaction tx = null;
		String viewbarId = null;

		loadProps();
		redirectIn(args);
		System.out.print("Please input the target directory(" + DEF_TARGET_DIRECTORY + "): ");
		String targetDir = in.readLine();
		if (targetDir.equals("")) {
			targetDir = DEF_TARGET_DIRECTORY;
		}
		do {
			System.out.print("Please input the viewbarId: ");
			viewbarId = in.readLine();
		} while(viewbarId.equals(""));
		System.out.print("Please input the patch zip filename(" + DEF_ZIP_FILE + "): ");
		String zipFile = in.readLine();
		if (zipFile.equals("")) {
			zipFile = DEF_ZIP_FILE;
		}
		System.out.print("Please input the root path(" + DEF_ROOT_PATH + "): ");
		String rootPath = in.readLine();
		if (rootPath.equals("")) {
			rootPath = DEF_ROOT_PATH;
		}
		try {
			config = new Configuration().configure("hibernate-addon.cfg.xml");
			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			VBViewbar vbViewbar = new VBViewbar();
			vbViewbar.setViewbarId(viewbarId);
			vbViewbar.setRootPath(rootPath);
			vbViewbar.setOsVersion("windows");
			vbViewbar.setCreateDate(new Date());
			vbViewbar.setReleaseDate(new Date());
			vbViewbar.setExpireDate(null);
			vbViewbar.setDownloadCount(new Long(0));
			vbViewbar.setDownloadSCount(new Long(0));
			session.save(vbViewbar);

			ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry entry;
			// 
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					File directory = new File(targetDir + "/" + viewbarId, entry.getName());
					if (!directory.exists()) {
						if (!directory.mkdirs()) {
							System.out.println("Create directory failed!");
							System.exit(0);
						}
					}
					zin.closeEntry();
				}
				if (!entry.isDirectory()) {
					File myFile = new File(entry.getName());
					File target = new File(targetDir + "/" + viewbarId);
					if (!target.exists()) {
						target.mkdirs();
						System.out.println(target.getPath() + " directory created!");
					}
					File createFile = new File(targetDir + "/" + viewbarId + "/" + myFile.getPath() + ".tmp");
					System.out.println(createFile.getPath() + " unziped!");
					if (!createFile.exists()) {

					}
					FileOutputStream fout = new FileOutputStream(createFile);
					DataOutputStream dout = new DataOutputStream(fout);
					DataInputStream dis = new DataInputStream(zin);
					byte[] b = new byte[(int) entry.getSize()];
					dis.readFully(b, 0, (int) entry.getSize());
					dout.write(b);
					VBFiles files = new VBFiles();
					files.setViewbarId(viewbarId);
					files.setFileName("/" + myFile.getPath().replace("\\", "/"));
					files.setFilePath("/" + viewbarId + "/" + myFile.getPath().replace("\\", "/") + ".tmp");
					files.setMd5(MD5HashUtil.hashCode(b));
					files.setDescription("");
					session.save(files);
					dout.close();
					fout.close();
					zin.closeEntry();
				}
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				tx.rollback();
			} catch(Exception e1) {}
		} finally {
			try {
				session.close();
			} catch(Exception e2) {}
		}
	}
}
