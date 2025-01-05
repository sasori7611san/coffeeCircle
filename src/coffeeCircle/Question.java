package coffeeCircle;

public class Question {
	private int number;
	private String question;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private int answer;
	Question(){
		this.number=0;
		this.question="";
		this.choice1="";
		this.choice2="";
		this.choice3="";
		this.choice4="";
		this.answer=0;
	}
	Question(int num,String quest,String c1,String c2,String c3,String c4,int ans){
		this.number=num;
		this.question=quest;
		this.choice1=c1;
		this.choice2=c2;
		this.choice3=c3;
		this.choice4=c4;
		this.answer=ans;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getChoice4() {
		return choice4;
	}
	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}
	public String getChoice3() {
		return choice3;
	}
	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}
	public String getChoice2() {
		return choice2;
	}
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
	public String getChoice1() {
		return choice1;
	}
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
