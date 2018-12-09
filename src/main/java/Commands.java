import org.telegram.telegrambots.api.methods.send.SendMessage;

enum Commands{
    START{
        @Override
        public String getBotAnswer() {
            return "Привет! Давай сыграем!\nДля начала, выберите тематику вопросов:";
        }

        @Override
        public void botReaction() { Bot.chatId.remove(id, Bot.chatId.get(id)); }

        @Override
        public SendMessage trySendMsg() { return CreateButtons.themeInline(id); }
    },
    HINT{
        @Override
        public String getBotAnswer() {
            if (Bot.chatId.get(id).getPoint() < 300)
                return "У тебя недостаточно очков";
            return null;
        }

        @Override
        public void botReaction() { Bot.chatId.get(id).correctAnswer("hint"); }

        @Override
        public SendMessage trySendMsg() {
            return CreateButtons.questInline(id, Bot.chatId.get(id).getPoint() < 300 ? Bot.quest : new Hint().getHint(Bot.quest));
        }
    },
    JOKE{
        @Override
        public String getBotAnswer() {
            if (Bot.chatId.get(id).getAmountJoke() != 0)
                return Bot.chatId.get(id).getJoke();
            return "Анектоды кончились";
        }

        @Override
        public void botReaction() {
            Bot.chatId.get(id).correctAnswer("joke");
        }

        @Override
        public SendMessage trySendMsg() { return CreateButtons.questInline(id, Bot.chatId.get(id).getQuest()); }
    },
    HELP{
        @Override
        public String getBotAnswer() {
            return "Правила очень простые: Я буду задавать вопросы, \n" +
                    "а тебе нужно будет выбрать из четырёх вариантов ответа единственно верный. \n" +
                    "За правильный ответ будет начислено 100 очков, за неправильный - отнято 300. \n" +
                    "Так же я могу немного помочь. Но увы не бесплатно. Цена в 300 очков меня вполне устроит. \n" +
                    "Как выиграть? Останься в плюсе до конца! Удачной игры!";
        }

        @Override
        public void botReaction() { }

        @Override
        public SendMessage trySendMsg() {
            return CreateButtons.questInline(id, Bot.quest);
        }
    };

    static Long id;
    public abstract String getBotAnswer();
    public abstract void botReaction();
    public abstract SendMessage trySendMsg();
}