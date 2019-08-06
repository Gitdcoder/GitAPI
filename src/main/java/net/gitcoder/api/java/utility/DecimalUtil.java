package net.gitcoder.api.java.utility;

import lombok.experimental.UtilityClass;

import java.text.DecimalFormat;

/**
 * @Author GitCoder.
 * <p>
 * The code is developed by the developer GitCoder.
 * You cannot change this code or interact with it in any way.
 * <p>
 * All right's is reserved.
 */
@UtilityClass
public class DecimalUtil {

    private final DecimalFormat decimalFormat = new DecimalFormat("#,###");

    public final String getStringFormat(int count) {
        return decimalFormat.format(count);
    }
}
