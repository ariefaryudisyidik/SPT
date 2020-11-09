package com.ariefaryudisyidik.pakdosen;

import java.io.*;
import java.net.*;
import java.util.*;

public class PakDosen {
	public final static int socket_pakdosen = 4786;
	public static String Lokasi_file;

	public static void main(String[] args) throws IOException {
		FileInputStream FIS = null;
		BufferedInputStream BIS = null;
		OutputStream OS = null;
		ServerSocket pakdosen = null;
		Socket mahasiswa = null;
		Lokasi_file = null;

		System.out.println("Input alamat file yang akan di distribusikan = ");
		Scanner scanner = new Scanner(System.in);
		Lokasi_file = scanner.nextLine();

		try {
			pakdosen = new ServerSocket(socket_pakdosen);
			while (true) {
				System.out.println("Menunggu permintaan mahasiswa ...");
				try {
					mahasiswa = pakdosen.accept();
					System.out.println("Terkoneksi mahasiswa = " + mahasiswa);

					File quis = new File(Lokasi_file);
					byte[] ukuranquis = new byte[(int) quis.length()];

					FIS = new FileInputStream(quis);
					BIS = new BufferedInputStream(FIS);
					BIS.read(ukuranquis, 0, ukuranquis.length);
					OS = mahasiswa.getOutputStream();

					System.out.println("Mengirimkan = " + Lokasi_file
							+ "Sebesar = " + quis.length() + "byte");
					OS.write(ukuranquis, 0, ukuranquis.length);
					OS.flush();
					System.out
							.println("Berhasil mengirimkan quis ke mahasiswa = ");
				} finally {
					if (BIS != null)
						BIS.close();
					if (OS != null)
						OS.close();
					if (mahasiswa != null)
						mahasiswa.close();
				}
			}
		} finally {
			if (pakdosen != null)
				pakdosen.close();
		}
	}
}
