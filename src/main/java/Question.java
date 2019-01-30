import java.io.File;

class Question {
    String text;
    String[] answers;
    String rightAnswer;
    File picture;
    Question(String lastQ, String lastA, String theme){
        String[] s = lastQ.split(":");
        answers = s[1].split("; ");
        text = s[0];
        rightAnswer = lastA;
        if(theme.equals("Кино")){
            picture = new File(text);
            text = "Из какого фильма этот кадр";
        }
    }
}

