package net.gitcoder.api.java.utility;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
public enum FormatTime {

    YEARS(" год", " года", " лет"),
    MONTHS(" месяц", " месяца", " месяцев"),
    WEEK(" неделя", " недели", " недель"),
    DAYS(" день", " дня", " дней"),
    HOURS(" час", " часа", " часов"),
    MINUTES(" минута", " минуты", " минут"),
    SECONDS(" секунда", " секунд", " секунд"),
    PLAYERS(" игрок"," игрока", " игроков"),
    MONEY(" монета", " монет", " монет");

    String one;
    String two;
    String three;

    FormatTime(String one,
               String two,
               String three) {

        this.one = one;
        this.two = two;
        this.three = three;
    }
}

