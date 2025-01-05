package coffeeCircle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuizApp {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// ファイル読み込みに必要なクラス
		FileInputStream fi = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		try {
		    //読み込みファイルのインスタンス生成
		    //ファイル名を指定する
		    fi = new FileInputStream("quiz_table.csv");
		    is = new InputStreamReader(fi,"Shift-JIS");
		    br = new BufferedReader(is);
		    //読み込み行
		    String line;
		    //読み込み行数の管理
		    int k = 0;

			//得点
			int score=0;
			//問題個数
			int questionCount=3;
			//問題文
			String[] questions =new String[questionCount];
			//問題文に対応した選択肢1
			String[] choice1s =new String[questionCount];
			//問題文に対応した選択肢2
			String[] choice2s =new String[questionCount];
			//問題文に対応した選択肢3
			String[] choice3s =new String[questionCount];
			//問題文に対応した選択肢4
			String[] choice4s =new String[questionCount];
			//問題文に対応した正解
			int[] answers =new int[questionCount];
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
			//問題セット
			Question question = new Question();
			
			//要素番号を抽選し、問題や答えを選択
			while(suffers.size() < questionCount) { //リストが全要素カバーするまでループ
				j = random.nextInt(questions.length);
				//要素番号がダブっていなかったら、リストに登録し、そのまま使う。
				//ダブっていたら、もう一度ランダムな値を取得して繰り返す。
				if(!suffers.contains(j)) {
					suffers.add(j);
				}
			}
			
			//問題の選別
			while((line = br.readLine()) != null) {
				//先頭行は列名の為除外
				if(k != 0) {
					//カンマで分割した内容を配列に格納する
					//{No.,問題,選択肢1,選択肢2,選択肢3,選択肢4,答え}
					String[] data = line.split(",");
					question.setNumber(Integer.parseInt(data[0]));
					question.setQuestion(data[1]);
					question.setChoice1(data[2]);
					question.setChoice2(data[3]);
					question.setChoice3(data[4]);
					question.setChoice4(data[5]);
					question.setAnswer(Integer.parseInt(data[6]));
					//問題の抽選確認
					//配列に格納する要素番号
					int l = 0;
					for(Integer num : suffers) {
						if(num + 1 == question.getNumber()) {
							questions[l]=question.getQuestion();
							choice1s[l]=question.getChoice1();
							choice2s[l]=question.getChoice2();
							choice3s[l]=question.getChoice3();
							choice4s[l]=question.getChoice4();
							answers[l]=question.getAnswer();
						}
						//格納要素番号のインクリメント
						l++;
					}
				}
				//行数のインクリメント
				k++;
			}

			//クイズの実行
			for(int i=0; i<questionCount; i++) {
				//問題を出力
				System.out.println("問題"+(i+1)+":");
				System.out.println(questions[i]);
				System.out.println("1."+choice1s[i]);
				System.out.println("2."+choice2s[i]);
				System.out.println("3."+choice3s[i]);
				System.out.println("4."+choice4s[i]);
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
	            }else if(userAnswer == answers[i]) {
	                long endTime = System.currentTimeMillis();
	                int elapsedTime = (int) ((endTime - startTime) / 1000);
	                score = Math.max(10 - elapsedTime, 1); // 解答時間に応じて点数を変動
	                totalScore += score;
					System.out.println("正解！"+score+"点獲得");
					score++;
				}else{
					System.out.println("不正解。正解は"+answers[suffers.get(i)]+"でした。");
				}
			}
			//結果を表示
			System.out.println("お疲れ様でした。");
			System.out.println("合計得点："+totalScore+"点");
			scanner.close();

		}catch(Exception e2) {
			e2.printStackTrace();
		}

	}

}
