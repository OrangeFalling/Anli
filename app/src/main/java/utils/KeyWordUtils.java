package utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: created by song
 * description: 改变关键字文字颜色工具类。
 */
public class KeyWordUtils {
    /**
     * function: 在一段文字中实现关键字改变颜色
     * @param color: 关键字要改变的颜色
     * @param text: 所有的文字
     * @param keyWord: 目标关键字集合
     * @return
     */
    public static SpannableString matcherSearchTitle(int color, String text,String[] keyWord){
        SpannableString spText = new SpannableString(text);   //将所有的文字转变为SpannableString类型。
        for (int i=0; i<keyWord.length; i++){
            //正则表达式，判断是否包含关键字。
            Pattern pattern = Pattern.compile(keyWord[i]);
            Matcher matcher = pattern.matcher(spText);
            while (matcher.find()){
                int startPosition = matcher.start();
                int endPosition = matcher.end();
                // Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 意为不包含起始位置和结束位置。
                // 改变关键字颜色。
                spText.setSpan(new ForegroundColorSpan(color),startPosition,endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spText;
    }
}
