package coffeeCircle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuizApp {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		//問題と選択肢
		String[] questions = {
				"次のうち最初にコーヒーを飲んだと言われる伝説の人物は？\n1.エチオピアの羊飼い\n2.インドの王様\n3.アメリカの農夫\n4.アラビアの商人",
				"150gのブラックコーヒーのカロリー量は？\n1.0kcal\n2.6kcal\n3.12kcal\n4.18kcal",
				"2022年時点でコーヒー豆の生産量１位はブラジルですが、2位は？\n1.インドネシア\n2.エチオピア\n3.コロンビア\n4.ベトナム",
				"次の国のうち2023年時点でコーヒー豆の消費量が一番多いのは？\n1.イタリア\n2.オランダ\n3.ドイツ\n4.フランス",
				"次の国のうち2022年時点で一人当たりのコーヒー消費量が一番多いのは？\n1.アメリカ\n2.日本\n3.フィンランド\n4.ルクセンブルク"
		};
		//正解
		int[] answers = {1,2,4,3,4};
		//得点
		int score=0;
		//問題個数
		int questionCount=3;
		//制限時間
		int timeLimit=10;
		//合計得点
		int totalScore=0;
		//ユーザー入力の準備
		Scanner scanner = new Scanner(System.in);
		//使用した要素番号のダブりリスト
		ArrayList<Integer> suffers = new ArrayList<>();
		//ランダム値を取る為のインスタンスを取得
		Random random = new Random();
		//ランダムの値取得
		int j=0;
		
		//要素番号を抽選し、問題や答えを選択
		while(suffers.size() < questionCount) { //リストが全要素カバーするまでループ
			j = random.nextInt(questions.length);
			//要素番号がダブっていなかったら、リストに登録し、そのまま使う。
			//ダブっていたら、もう一度ランダムな値を取得して繰り返す。
			if(!suffers.contains(j)) {
				suffers.add(j);
			}
		}

		//クイズの実行
		for(int i=0; i<questionCount; i++) {
			//問題を出力
			System.out.println("問題"+(i+1)+":");
			System.out.println(questions[suffers.get(i)]);
			System.out.println("答えを入力してください（1，2，3，4）：");
			System.out.println("制限時間は"+timeLimit+"秒です。");
            // 解答時間を計測
            long startTime = System.currentTimeMillis();
            int userAnswer = -1;
            boolean answered = false;
            //制限時間内に入力を受け付ける
            for (int remainingTime = timeLimit; remainingTime > 0; remainingTime--) {
                System.out.print("残り時間: " + remainingTime + " 秒\r");
                try {
	                Thread.sleep(1000); // 1秒待機
	
	                // ユーザーの入力があれば処理する
	                if (System.in.available() > 0) { // 入力があるか確認
	                    userAnswer = scanner.nextInt();
	                    answered = true;
	                    break;
	                }
                }catch(Exception e) {
                	e.printStackTrace();
                }
            }

			//正誤判定
            if(!answered) {
            	System.out.println("時間切れです！");
            }else if(userAnswer == answers[suffers.get(i)]) {
                long endTime = System.currentTimeMillis();
                int elapsedTime = (int) ((endTime - startTime) / 1000);
                score = Math.max(10 - elapsedTime, 1); // 解答時間に応じて点数を変動
                totalScore += score;
				System.out.println("正解！"+score+"点獲得");
				score++;
			}else {
				System.out.println("不正解。正解は"+answers[suffers.get(i)]+"でした。");
			}
		}
		//結果を表示
		System.out.println("お疲れ様でした。");
		System.out.println("合計得点："+totalScore+"点");
		scanner.close();

	}

}
