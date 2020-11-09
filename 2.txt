package com.ariefaryudisyidik.mahasiswa;

import java.io.*;
import java.net.*;
import java.util.*;

public class Mahasiswa {
	public final static int socket_pakdosen = 4786;
	public static String ip_pakdosen = null;
	public static String Lokasi_penyimpanan;
	public final static int ukuran_file = 20000000;

	public static void main(String[] args) throws IOException {
		int byte_terbaca;
		int hasil_byte = 0;

		FileOutputStream FOS = null;
		BufferedOutputStream BOS = null;
		Socket mahasiswa = null;
		Lokasi_penyimpanan = null;

		System.out.println("Input IP Pak Dosen = ");
		Scanner scanner = new Scanner(System.in);
		ip_pakdosen = scanner.nextLine();

		try {
			mahasiswa = new Socket(ip_pakdosen, socket_pakdosen);
			System.out.println("Menghubungi pak dosen ...");

			System.out.println("Input Tempat Penyimpanan quis = ");
			Scanner scanner2 = new Scanner(System.in);
			Lokasi_penyimpanan = scanner2.nextLine();

			byte[] ukuranquis = new byte[ukuran_file];
			InputStream IS = mahasiswa.getInputStream();
			FOS = new FileOutputStream(Lokasi_penyimpanan);
			BOS = new BufferedOutputStream(FOS);

			byte_terbaca = IS.read(ukuranquis, 0, ukuranquis.length);
			hasil_byte = byte_terbaca;

			do {
				byte_terbaca = IS.read(ukuranquis, hasil_byte,
						(ukuranquis.length - hasil_byte));
				if (byte_terbaca >= 0)
					hasil_byte += byte_terbaca;
			} while (byte_terbaca > -1);

			BOS.write(ukuranquis, 0, hasil_byte);
			BOS.flush();
			System.out.println("File = " + Lokasi_penyimpanan
					+ " sudah di copy silahkan cek.. ukurannya sebesar "
					+ hasil_byte + " bytes");

		} finally {
			if (FOS != null)
				FOS.close();
			if (BOS != null)
				BOS.close();
			if (mahasiswa != null)
				mahasiswa.close();
		}
	}
}
