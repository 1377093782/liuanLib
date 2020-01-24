package com.anguomob.tran;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by liuan on 2018/1/11.
 */

public class multiLanguageA {
    public static void main(String[] args) {
        start();
    }

    static String ORIGIN_PATH = System.getProperty("user.dir") + File.separator + "app\\src\\main\\java\\com\\anguo\\easytouch\\translation";

    private static void start() {
        //脱壳
        // int buzhou = 1;
        //群翻译 也可以单翻译 注意格式en----
        int buzhou = 2;
        //生成翻译文本  af----ar----cs----de----en----es----fr----hi----hr----in----it----ja----ms----pt----ro----sv----th----tl----zh----
        // int buzhou = 3;
        // 根据yuyan22生成翻译目录  valus-ar/string.xml
        // int buzhou = 4;


        String myText = "MyText";
        String myTextEx = "MyTextEx";
        String newText = "newText";
        String finalText = "finalText";
        String xmlPath = "xmlPath";
        String otherStr = "..........."; // 先执行步骤1 然后翻译完毕 然后执行步骤2
        //  String overStrXml = "D:\\work\\battery\\battery5\\battery\\app\\src\\main\\res"; // 先执行步骤1 然后翻译完毕 然后执行步骤2
        String overStrXml = System.getProperty("user.dir") + File.separator + "app\\src\\main\\res"; // 先执行步骤1 然后翻译完毕 然后执行步骤2
        //根据overStrXml 计算出来 yuyan22
        String yuyan22 = getByGenDirStr(); // 先执行步骤1 然后翻译完毕 然后执行步骤2

        String exceptStr = "zh----";
        yuyan22=yuyan22.replace(exceptStr,"");
        String pathname = ORIGIN_PATH + "\\tmp\\originTextView";
        if (buzhou == 1) {
            one(pathname, myText, newText, otherStr);
            File myTextfile = new File(pathname + myTextEx);
            String[] multiLanguage = yuyan22.split("----");
            String separated = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
            createMutiLauguageMyTextEx(myTextfile, multiLanguage, separated);
        } else if (buzhou == 2) {
            System.out.println(yuyan22 + "Ready to copy");
            two_22(pathname, myTextEx, newText, otherStr, finalText, yuyan22, overStrXml);
            System.out.println(yuyan22 + "Copy completed");
        } else if (buzhou == 3) {
            System.out.println("Please copy the following to yuyan22 Then go to step 2");
            extractMultipleLanguageNames(overStrXml);

        } else if (buzhou == 4) {
            System.out.println("Create initial documents based on multilingual countries and <yuan22>");
            createValuesFils(yuyan22, overStrXml);
            System.out.println("Create sucess");
        }
    }

    private static String getYuYan22(String overStrXml) {
        File file = new File(overStrXml);
        String res = "";
        String split = "----";
        //文件不存在
        if (!file.exists()) {
            System.out.println("file is exists");
            System.exit(-1);
        }
        if (!file.isDirectory()) {
            System.out.println("file is not directory");
            System.exit(-1);
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            if (Pattern.matches("values-[a-zA-Z]\\d?", name)) {
                continue;
            }
            if (name.startsWith("values-")) {
                res += name.replace("values-", "");
                if (i != files.length) {
                    res += split;
                }
            }
        }
        //写上对应列表 为了更好的寻找语言

        return res;

    }

    private static String getByChinese(String tags) {

        LinkedHashMap <String, String> hm = getAllLanguage();
        String s = hm.get(tags);
        if (s != null) {
            return s;
        } else {
            System.out.println("请添加关于【" + tags + "】的中文(简体)版语言");
            System.exit(-1);
            return "";
        }
    }

    private static String getByGenDirStr() {
        String res = "";
        LinkedHashMap<String, String> hm = getAllLanguage();

        Iterator<Map.Entry<String, String>> iterator = hm.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            res += next.getKey() + "----";
        }

        return res;
    }

    public static String readJsonFile(String fileName) {
        File file = new File(fileName);
        HashMap<String, String> hm = new HashMap<>();
        if (!file.exists()) {
            System.out.println("请检查【" + fileName + "】文件是否存在");
            return null;
        }
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static LinkedHashMap <String, String> getAllLanguage() {
        //遍历 文件language.json 拿到所有的语言
        LinkedHashMap <String, String> hm = new LinkedHashMap <>();
        String s = readJsonFile(ORIGIN_PATH + "\\language.json");
        Pattern compile = Pattern.compile("\"code\": \"(.*?)\"([\\s\\S]*?)\"name\": \"(.*?)\"");
        Matcher matcher = compile.matcher(s);
        int count = 0;
        while (matcher.find()) {
            count++;
            String code = matcher.group(1);
            String name = matcher.group(3);
            hm.put(code, name);
        }


        return hm;

//        hm.put("ar", "阿拉伯语");
//        hm.put("sq", "阿尔巴尼亚语");
//        hm.put("af", "布尔语(南非荷兰语)");
//        hm.put("de", "德语");
//        hm.put("es", "西班牙语");
//        hm.put("hi", "印地语");
//        hm.put("in", "印度语");
//        hm.put("fr", "法语");
//        hm.put("bn", "孟加拉语");
//        hm.put("it", "意大利语");
//        hm.put("ja", "日语");
//        hm.put("pt", "葡萄牙语");
//        hm.put("zh", "中文(简体)");
//        hm.put("ru", "俄语");
//        hm.put("bg", "保加利亚文");
//        hm.put("ca", "加泰罗尼亚文");
//        hm.put("cs", "捷克语");
//        hm.put("cy", "塞浦路斯(土耳其语或者希腊)");
//        hm.put("da", "丹麦语");
//        hm.put("el", "希腊语");
//        hm.put("et", "爱沙尼亚语");
//        hm.put("fa", "波斯语");
//        hm.put("fi", "芬兰语");
//        hm.put("ht", "海地语");
//        hm.put("hu", "胸牙利语");
//        hm.put("id", "印尼语");
//        hm.put("iw", "希伯来语");
//        hm.put("ko", "韩语");
//        hm.put("lt", "立陶宛语");
//        hm.put("lv", "拉脱维尼亚语");
//        hm.put("mt", "马耳他语");
//        hm.put("nl", "荷兰语");
//        hm.put("no", "挪威语");
//        hm.put("sk", "斯洛伐克语");
//        hm.put("sl", "斯洛文尼亚语");
//        hm.put("sv", "瑞典语");
//        hm.put("uk", "乌克兰语");
//        hm.put("ur", "乌尔都语");
//        hm.put("ka", "哈萨克斯坦");
//        hm.put("tr", "土耳其");
//        hm.put("pl", "波兰语");
//        hm.put("th", "泰语");
//        hm.put("ro", "罗马尼亚语");
//        hm.put("sw", "肯尼亚斯瓦希里语");
//        hm.put("tl", "菲律宾语");
//        hm.put("vi", "越南语");
//        hm.put("ms", "马来语");
//        hm.put("zu", "南非祖鲁语");
//        hm.put("en", "英语");
//        return hm;
    }

    private static void createValuesFils(String yuyan22, String overStrXml) {

        String[] split = yuyan22.split("----");
        for (int i = 0; i < split.length; i++) {
            File file = new File(overStrXml, "values-" + split[i]);
            if (file.exists()) {
                System.out.println("Do not need to create");
            } else {
                file.mkdir();

                FileOutputStream fileOutputStream = null;
                try {
                    File file1 = new File(file.getAbsolutePath(), "strings.xml");
                    file1.createNewFile();
                    fileOutputStream = new FileOutputStream(file1);

                    fileOutputStream.write(("<resources>\n" + "</resources>").getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Created " + file.getName() + " Sucess");

            }
        }

    }

    private static void extractMultipleLanguageNames(String overStrXml) {
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(overStrXml);
        File[] files = file.listFiles();
        Pattern p = Pattern.compile("values-[a-zA-Z]\\d");
        for (File file1 : files) {
            String name = file1.getName();
            if (p.matcher(name).find()) {
                continue;
            }
            if (name.contains("values-")) {
                String replace = name.replace("values-", "");
                stringBuffer.append(replace + "----");
            }
        }
        System.out.println(stringBuffer.toString());

    }

    private static final String TAG = "multiLanguage";

    private static void two_22(String fileName, String myTextEx, String newText, String otherStr, String finalText, String yuyan22, String overStrXml) {
        String[] multiLanguage = yuyan22.split("----");
        System.out.println("You have to do it " + multiLanguage.length + "multi-language");
        System.out.println("I ask you for the first time I can help you format it Well you then copied to<originTextViewMyTextEx.xml>file");
        System.out.println("If it is the first time to do so, please deduct 1 and press Enter");
        String separated = "\n?(.*?)@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
        File myTextfile = new File(fileName + myTextEx);


        //翻译脚本开始了
        //读取文本并且分割
        String contentText = readToString(myTextfile.getAbsolutePath());
        String newTextStr = readToString(fileName + newText);


        String[] multiLanguageContent = contentText.split(separated);
        if (multiLanguage.length != multiLanguageContent.length - 1) {
            System.out.println("The current parameters do not match");
            return;
        }
        String[] newTextContent = newTextStr.split("\n");
//        System.out.println("multiLanguageContent" + Arrays.toString(multiLanguageContent));
//        System.out.println("newTextContent" + Arrays.toString(newTextContent));
        StringBuffer stringBuffer = new StringBuffer();
        for (int i1 = 0; i1 < multiLanguage.length; i1++) {
            //对于单个多语言操作
            //读取翻译好的文件  和带特殊字符的文件 然后合成 新的文件
            //new 是带...的
            String tempString = null;
            StringBuffer sbCurrentText = new StringBuffer();
            try {
                //new 是带...的
                String[] myTextreader = multiLanguageContent[i1 + 1].split("\n");
//                System.out.println("myTextreader" + Arrays.toString(myTextreader));
                for (int i2 = 0; i2 < myTextreader.length; i2++) {
                    // 为了过滤分隔符号
                    String translate = myTextreader[i2];
                    if (i2 == 0 && myTextreader.length != 1 && multiLanguage[i2].equals(multiLanguage[i1])) {
                        System.out.println("" + multiLanguage[i2]);
                        String str = "The current multilingual translation is " + ">>>>>>>>>>" + multiLanguage[i1] + "<<<<<<<<<<";
                        System.out.println(str);
                        stringBuffer.append(str + "\n");
                        continue;
                    } else if (i2 == 0 && myTextreader.length != 1) {
                        String str = "IMHO At present, there are mistakes in multiple languages" + ">>>>>>>>>>" + multiLanguage[i1] + "<<<<<<<<<<";
                        System.out.println(str);
                        continue;
                    }
                    if (myTextreader.length == 1) {
                        String str = "The current multilingual translation is " + ">>>>>>>>>>" + multiLanguage[i1] + "<<<<<<<<<<";
                        System.out.println(str);
                        tempString = newTextContent[i2];
                    } else {
                        tempString = newTextContent[i2 - 1];
                    }

//                    System.out.println("tempString" + tempString);
                    translate = translate.replace("％1 $ d", "%1$d");
                    translate = translate.replace("? 1 $ d", "%1$d");
                    translate = translate.replace("％2 $ d", "%2$d");
                    translate = translate.replace("? 2 $ d", "%2$d");
                    translate = translate.replace("% 1 $ d", "%1$d");
                    translate = translate.replace("% 2 $ d", "%2$d");
                    translate = translate.replace("％d", "%d");
                    translate = translate.replace("? D", "%d");
                    translate = translate.replace("% D", "%d");
//google有一些固定的翻译格式 改成正确的
                    translate = translate.replace("％ 1 $ s", "%1$s");
                    translate = translate.replace("％1 $ S", "%1$s");
                    translate = translate.replace("% 1 $ s", "%1$s");
                    translate = translate.replace("％1 $ s", "%1$s");
                    translate = translate.replace("1% $ s", "%1$s");
                    translate = translate.replace("% 1 $", "%1$s");
                    translate = translate.replace("? 1 $ s", "%1$s");
                    translate = translate.replace("% 2 $", "%2$s");
                    translate = translate.replace("% 2 $", "%2$s");


                    translate = translate.replace("％2 $ s", "%2$s");
                    translate = translate.replace("? 2 $ s", "%2$s");
                    translate = translate.replace("％ 2 $ s", "%2$s");
                    translate = translate.replace("% 2 $ s", "%2$s");
                    translate = translate.replace("2% $ s", "%2$s");


                    translate = translate.replace("& amp", "&");
                    translate = translate.replace("& \u200B\u200Bamp", "&");

                    translate = translate.replace("％s", "%s");
                    translate = translate.replace("％ s", "%s");
                    translate = translate.replace("% s", "%s");
                    translate = translate.replace("% S", "%s");
                    translate = translate.replace("? s", "%s");
                    translate = translate.replace("? S", "%s");
                    translate = translate.replace("％S", "%s");
                    translate = translate.replace("٪ s", "%s");

                    translate = translate.replace("</ i>", "</i>");
                    translate = translate.replace("\\ \"", "\\\"");

                    translate = translate.replace("\\ r\\ n", "\\r\\n");

                    translate = translate.replace("\\ n", "\\n");
                    translate = translate.replace("\\ N", "\\n");
                    translate = translate.replace("\\ п", "\\n");
                    translate = translate.replace("? ?", "%s");
                    translate = translate.replace("? 1S", "%1s");
                    translate = translate.replace("% 1s", "%1s");
                    translate = translate.replace("％ 1s", "%1s");
                    translate = translate.replace("％1s", "%1s");

                    translate = translate.replace("\\ r \\n", "\\r\\n");
                    if (tempString.contains("string") && tempString.contains("name") && tempString.contains("=") && tempString.contains(">")) {
                        //把文件的其他字符 替换成myText中的字符
                        translate = "\"" + translate.trim() + "\"";
                        String replace = tempString.replace(otherStr, translate);
                        stringBuffer.append(replace + " \n");
                        sbCurrentText.append(replace + " \n");
                    }
//写出到xml
                }
                File file = new File(overStrXml + "\\values-" + multiLanguage[i1], "strings.xml");
                //     System.out.println(overStrXml + "\\values-" + multiLanguage[i1]+ "strings.xml");
                writeToXml(file, multiLanguage[i1], sbCurrentText);
                write2Local(fileName, finalText, stringBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    private static void writeToXml(File file, String s, StringBuffer sbCurrentText) throws IOException {
        File file2 = file;
        if (!file2.exists()) {
            System.out.println("What to do ghost >>>>>>>>>>" + s + "<<<<<<<<<< resource file does not exist can not be copied replacement");
            return;
        } else {
//去掉文件最后的resources
            StringBuffer stringBuffer3 = new StringBuffer();
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
            String tempString2 = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString2 = bufferedReader2.readLine()) != null) {

                if (tempString2.contains("</resources>")) {
                    tempString2 = tempString2.replace("</resources>", "");
                    stringBuffer3.append(tempString2 + " \n");
                } else if (tempString2.contains("\n")) {
                    stringBuffer3.append(tempString2);
                } else {
                    stringBuffer3.append(tempString2 + " \n");
                }

            }

            //补上
            FileOutputStream fileOutputStream3 = new FileOutputStream(file2, false);

            fileOutputStream3.write(stringBuffer3.toString().getBytes());
            fileOutputStream3.close();
            bufferedReader2.close();
            //补上
            FileOutputStream fileOutputStream2 = new FileOutputStream(file2, true);
            fileOutputStream2.write((sbCurrentText + "</resources>").getBytes());
//            System.out.println("sbCurrentText" + sbCurrentText);
            fileOutputStream2.close();
            System.out.println(s + " It's Ok");
        }
    }

    private static void write2Local(String fileName, String finalText, StringBuffer stringBuffer) throws IOException {
        String text = stringBuffer.toString();
        //写到最终的文件里
        File file1 = new File(fileName + finalText);
        if (file1.exists()) {
            file1.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(text.getBytes());
        fileOutputStream.close();
    }

    public static String readToString(String fileName) {

        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent);
        } catch (Exception e) {
//            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    private static void createMutiLauguageMyTextEx(File myTextfile, String[] multiLanguage, String separated) {

        FileOutputStream fileOutputStream3 = null;
        try {
            fileOutputStream3 = new FileOutputStream(myTextfile, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < multiLanguage.length; i++) {
            getByChinese(multiLanguage[i]);
        }
        for (int i = 0; i < multiLanguage.length; i++) {
            String byChinese = getByChinese(multiLanguage[i]);
            try {
                fileOutputStream3.write((byChinese + separated + multiLanguage[i] + "\n\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            fileOutputStream3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void one(String pathname, String myText, String newText, String otherStr) {

        //步骤1  读取xml文件 并且翻译完毕 生成.txt 并且手动替换翻译过的文件
        readFileByLines(pathname, myText);
        //步骤2
        //生成 带转义符的...........文件
        produceDianDianDian(pathname, newText, otherStr);
    }


    public static void readFileByLines(String fileName, String myText) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            System.out.println("Copy the following content to google translation.............." + myText);
            try {

                reader = new BufferedReader(new FileReader(file));

            } catch (FileNotFoundException e) {
                System.out.println(file + "文件名不正确");
                System.exit(-1);
            }
            String tempString = null;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if (tempString.contains("esources>")) {
                    continue;
                }

                if (tempString.contains("string") && tempString.contains("name") && tempString.contains("=") && tempString.contains(">")) {
                    tempString = tempString.substring(tempString.indexOf(">") + 1);
                    tempString = tempString.replace("</string>", "");


//                    System.out.println("提出出的字符串|" + tempString + "| 长度为" + tempString.length());
                    System.out.println(tempString);
                    //去掉分号
                    if (tempString.substring(0, 1).equals("\"") && (tempString.substring(tempString.length() - 1, tempString.length()).equals("\""))) {
                        tempString = tempString.substring(1, tempString.length() - 1);
                    }
                    if (tempString.substring(0, 1).equals("\"") && (tempString.substring(tempString.length() - 4, tempString.length()).equals("\"   "))) {
                        tempString = tempString.substring(1, tempString.length() - 4);
                    }
                } else {
                    stringBuffer.append("\n");
                }

                stringBuffer.append(tempString + "\n");


            }
            write2Local(fileName, myText, stringBuffer);

//            System.out.println(text);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    private static void produceDianDianDian(String fileName, String newText, String otherStr) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            System.out.println("Generate special characters " + otherStr + "The file generated by this directory " + newText);
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.contains("esources>")) {

                    continue;
                }
                String bgStr = tempString;
                ;
                if (tempString.contains("string") && tempString.contains("name") && tempString.contains("=") && tempString.contains(">")) {
                    tempString = tempString.replace("</string>", "");
                    String substring = bgStr.substring(0, bgStr.indexOf(">") + 1);
                    stringBuffer.append(substring + otherStr + "</string> \n");

                } else {
                    stringBuffer.append(tempString + " \n");
                }


            }
            String text = stringBuffer.toString();
            FileOutputStream fileOutputStream = new FileOutputStream(fileName + newText);
            fileOutputStream.write(text.getBytes());
            fileOutputStream.close();

            System.out.println(text);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }


}