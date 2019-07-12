/**
 *
 */
package dict.build;

import java.util.List;

/**
 * @author zhangcheng
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
//        args = new String[]{"/Users/zhaozengbin/data/duia-ml/jianda_answer.txt", "/Users/zhaozengbin/git/HanLP/data/dictionary/custom/CustomDictionary.txt"};
        if (args.length == 0) {
            System.out.println("命令格式:command 需要分析的文本地址(必要) 词性(非必要 默认nz) 过滤词典文本地址(必须为utf-8编码 且已空格或table为词语格式分隔符且按行分隔)");
            return;
        }

        String rawpath = null;
        String existingWordFilePath = null;
        String natureType = null;
        if (args.length > 0) {
            rawpath = args[0];
        }
        if (args.length > 1) {
            natureType = args[1];
        }
        if (args.length > 1) {
            existingWordFilePath = args[2];
        }

        String left = null;
        String right = null;
        String entropyfile = null;

        FastBuilder builder = new FastBuilder();

        if (null == right)
            right = builder.genFreqRight(rawpath, 6, 10 * 1024);
        if (null == left)
            left = builder.genLeft(rawpath, 6, 10 * 1024);
        if (null == entropyfile)
            entropyfile = builder.mergeEntropy(right, left);
        List<String> wordList = null;
        try {
            wordList = builder.readExistingWordFile(existingWordFilePath.split(","));
        } catch (Exception e) {
            e.printStackTrace();
        }
        builder.extractWords(right, entropyfile, natureType, wordList);
    }
}
