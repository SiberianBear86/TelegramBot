class Question {
    String text;
    String[] answers;
    String rightAnswer;
    Question(String lastQ, String lastA){
        String[] s = lastQ.split(":");
        answers = s[1].split("; ");
        text = s[0];
        rightAnswer = lastA;
    }
}

