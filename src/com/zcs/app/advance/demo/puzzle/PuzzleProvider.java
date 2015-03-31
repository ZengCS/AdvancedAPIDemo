package com.zcs.app.advance.demo.puzzle;

import com.zcs.app.advance.R;

public class PuzzleProvider {
	public static final int TYPE_9 = 9;
	public static final int TYPE_16 = 16;

	public static class Nobita {
		public static int type = TYPE_16;
		private static int ids[] = new int[type];

		public static int[] puzzle() {
			ids[0] = R.drawable.nobita_01;
			ids[1] = R.drawable.nobita_02;
			ids[2] = R.drawable.nobita_03;
			ids[3] = R.drawable.nobita_04;
			ids[4] = R.drawable.nobita_05;
			ids[5] = R.drawable.nobita_06;
			ids[6] = R.drawable.nobita_07;
			ids[7] = R.drawable.nobita_08;
			ids[8] = R.drawable.nobita_09;
			ids[9] = R.drawable.nobita_10;
			ids[10] = R.drawable.nobita_11;
			ids[11] = R.drawable.nobita_12;
			ids[12] = R.drawable.nobita_13;
			ids[13] = R.drawable.nobita_14;
			ids[14] = R.drawable.nobita_15;
			ids[15] = R.drawable.nobita_16;
			return ids;
		}
	}

	public static class Doraemon {
		public static int type = TYPE_9;
		private static int ids[] = new int[type];

		public static int[] puzzle() {
			ids[0] = R.drawable.doraemon_01;
			ids[1] = R.drawable.doraemon_02;
			ids[2] = R.drawable.doraemon_03;
			ids[3] = R.drawable.doraemon_04;
			ids[4] = R.drawable.doraemon_05;
			ids[5] = R.drawable.doraemon_06;
			ids[6] = R.drawable.doraemon_07;
			ids[7] = R.drawable.doraemon_08;
			ids[8] = R.drawable.doraemon_09;
			return ids;
		}
	}

	public static class QQFamily {
		public static int type = TYPE_9;
		private static int ids[] = new int[type];

		public static int[] puzzle() {
			ids[0] = R.drawable.qq_family_01;
			ids[1] = R.drawable.qq_family_02;
			ids[2] = R.drawable.qq_family_03;
			ids[3] = R.drawable.qq_family_04;
			ids[4] = R.drawable.qq_family_05;
			ids[5] = R.drawable.qq_family_06;
			ids[6] = R.drawable.qq_family_07;
			ids[7] = R.drawable.qq_family_08;
			ids[8] = R.drawable.qq_family_09;
			return ids;
		}
	}
}
