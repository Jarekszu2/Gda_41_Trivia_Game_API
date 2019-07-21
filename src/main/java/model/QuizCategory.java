package model;

public enum QuizCategory {
    GENERAL_KNOWLEDGE("General Knowledge", 9),
    ANY("Any Category", -1),
    ENTERTAINMENT_BOOKS("Entertainment: Books", 10),
    ENTERTAINMENT_FILM("Entertainment: Film", 11),
    ENTERTAINMENT_MUSIC("Entertainment: Music", 12),
    ENTERTAINMENT_MUSICALS("Entertainment: Musicals &amp; Theatres", 13),
    ENTERTAINMENT_TV("Entertainment: Television", 14),
    ENTERTAINMENT_VIDEO_GAMES("Entertainment: Video Games", 15),
    SCIENCE_BOARD_GAMES("Entertainment: Board Games", 16),
    SCIENCE_NATURE("Science &amp; Nature", 17),
    SCIENCE_COMPUTERS("Science: Computers", 18),
    SCIENCE_MATHEMATICS("Science: Mathematics", 19),
    MYTHOLOGY("Mythology", 20),
    SPORTS("Sports", 21),
    GEOGRAPHY("Geography", 22),
    HISTORY("History", 23),
    POLITICS("Politics", 24),
    ART("Art", 25),
    CELEBRITIES("Celebrities", 26),
    ANIMALS("Animals", 27),
    VEHICLES("Vehicles", 28),
    ENTERTAINMENT_COMICS("Entertainment: Comics", 29),
    SCIENCE("Science: Gadgets", 30),
    ENTERTAINMENT_JAPANESE("Entertainment: Japanese Anime &amp; Manga", 31),
    ENTERTAINMENT_CARTOONS("Entertainment: Cartoon &amp; Animations", 32);
    private String name;
    private int id;

    QuizCategory(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
