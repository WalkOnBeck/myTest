package com.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleCalendar {
    long[] lunarInfo = new long[]{
            0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0, 0x55d2,
            0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd255, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977,
            0x497f, 0xa4b0, 0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970,
            0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
            0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2, 0xa950, 0xb557,
            0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0,
            0xaea6, 0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0,
            0x96d0, 0x4dd5, 0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
            0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46, 0xab60, 0x9570,
            0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
            0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5,
            0xa950, 0xb4a0, 0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
            0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260, 0xea65, 0xd530,
            0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520, 0xdd45,
            0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0,
            0x4b63, 0x937f, 0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
            0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4,
            0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4, 0xa5b0, 0x52b0,
            0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260,
            0xe968, 0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252,
            0xd520};
    List<Element> elements=new ArrayList<Element>();
    public static  Map<String,SimpleCalendar> cache=new HashMap<String,SimpleCalendar>();
    long[] solarMonth = new long[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String[] Gan = new String[]{"��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};
    String[] Zhi = new String[]{"��", "��", "��", "î", "��", "��", "��", "δ", "��", "��", "��", "��"};
    String[] Animals = new String[]{"��", "ţ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��"};
    String[] solarTerm = new String[]{"С��", "��", "����", "��ˮ", "����", "����", "����", "����", "����", "С��", "â��", "����", "С��", "����", "����", "����", "��¶", "���", "��¶", "˪��", "����", "Сѩ", "��ѩ", "����"};
    int[] sTermInfo = new int[]{0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758};
    char[] nStr1 = new char[]{'��', 'һ', '��', '��', '��', '��', '��', '��', '��', '��', 'ʮ'};
    String[] nStr2 = new String[]{"��", "ʮ", "إ", "ئ", " "};

    char[] jcName0 = new char[]{'��', '��', '��', 'ƽ', '��', 'ִ', '��', 'Σ', '��', '��', '��', '��'};
    char[] jcName1 = new char[]{'��', '��', '��', '��', 'ƽ', '��', 'ִ', '��', 'Σ', '��', '��', '��'};
    char[] jcName2 = new char[]{'��', '��', '��', '��', '��', 'ƽ', '��', 'ִ', '��', 'Σ', '��', '��'};
    char[] jcName3 = new char[]{'��', '��', '��', '��', '��', '��', 'ƽ', '��', 'ִ', '��', 'Σ', '��'};
    char[] jcName4 = new char[]{'��', '��', '��', '��', '��', '��', '��', 'ƽ', '��', 'ִ', '��', 'Σ'};
    char[] jcName5 = new char[]{'Σ', '��', '��', '��', '��', '��', '��', '��', 'ƽ', '��', 'ִ', '��'};
    char[] jcName6 = new char[]{'��', 'Σ', '��', '��', '��', '��', '��', '��', '��', 'ƽ', '��', 'ִ'};
    char[] jcName7 = new char[]{'ִ', '��', 'Σ', '��', '��', '��', '��', '��', '��', '��', 'ƽ', '��'};
    char[] jcName8 = new char[]{'��', 'ִ', '��', 'Σ', '��', '��', '��', '��', '��', '��', '��', 'ƽ'};
    char[] jcName9 = new char[]{'ƽ', '��', 'ִ', '��', 'Σ', '��', '��', '��', '��', '��', '��', '��'};
    char[] jcName10 = new char[]{'��', 'ƽ', '��', 'ִ', '��', 'Σ', '��', '��', '��', '��', '��', '��'};
    char[] jcName11 = new char[]{'��', '��', 'ƽ', '��', 'ִ', '��', 'Σ', '��', '��', '��', '��', '��'};

    //��������  *��ʾ�ż���
    String[] sFtv = new String[]{
            "0101*Ԫ��",
            "0106  �й�13���˿���",
            "0110  �й�110������",

            "0202  ����ʪ����",
            "0204  ���翹��֢��",
            "0210  ����������",
            "0214  ���˽�",
            "0221  ����ĸ����",
            "0207  ������Ԯ�Ϸ���",

            "0303  ȫ��������",
            "0308  ��Ů��",
            "0312  ֲ���� ����ɽ����������",
            "0315  ������Ȩ�汣����",
            "0321  ����ɭ����",
            "0322  ����ˮ��",
            "0323  ����������",
            "0324  ������ν�˲���",

            "0401  ���˽�",
            "0407  ����������",
            "0422  ���������",

            "0501*�����Ͷ���",
            "0504  �й������",
            "0505  ȫ����ȱ������",
            "0508  �����ʮ����",
            "0512  ���ʻ�ʿ��",
            "0515  ���ʼ�ͥ��",
            "0517  ���������",
            "0518  ���ʲ������",
            "0519  �й��봨���𰧵��� ȫ��������",
            "0520  ȫ��ѧ��Ӫ����",
            "0522  ���������������",
            "0523  ����ţ����",
            "0531  ����������",

            "0601  ���ʶ�ͯ��",
            "0605  ���绷����",
            "0606  ȫ��������",
            "0617  ���λ�Į���͸ɺ���",
            "0623  ���ʰ���ƥ����",
            "0625  ȫ��������",
            "0626  ���ʷ���Ʒ��",

            "0701  ������ ��ۻع������",
            "0707  ����ս��������",
            "0711  �����˿���",

            "0801  ��һ������",
            "0815  �ձ���ʽ����������Ͷ����",

            "0908  ����ɨä��",
            "0909  **����������",
            "0910  ��ʦ��",
            "0916  ���ʳ����㱣����",
            "0917  ���ʺ�ƽ��",
            "0918  �š�һ���±������",
            "0920  ���ʰ�����",
            "0927  ����������",
            "0928  ���ӵ���",

            "1001*����� �������ֽ� �������˽�",
            "1002  ���ʼ�����Ȼ�ֺ���",
            "1004  ���綯����",
            "1007  ����ס����",
            "1008  �����Ӿ��� ȫ����Ѫѹ��",
            "1009  ����������",
            "1010  �������������� ���羫��������",
            "1015  ����ä�˽�",
            "1016  ������ʳ��",
            "1017  ��������ƶ����",
            "1022  ���紫ͳҽҩ��",
            "1024  ���Ϲ���",
            "1025  �����컨������",
            "1026  ��������",
            "1031  ��ʥ��",

            "1107  ʮ������������������",
            "1108  �й�������",
            "1109  ����������",
            "1110  ���������",
            "1112  ����ɽ����",
            "1114  ����������",
            "1117  ���ʴ�ѧ����",

            "1201  ���簬�̲���",
            "1203  ����м�����",
            "1209  ����������",
            "1210  ������Ȩ��",
            "1212  �����±������",
            "1213  �Ͼ�����ɱ",
            "1220  ���Żع������",
            "1221  ����������",
            "1224  ƽ��ҹ",
            "1225  ʥ���� ����ǿ��������",
            "1226  **����"};
    //ũ������  *��ʾ�ż���
    String[] lFtv = new String[]{
            "0101*����",
            "0102*�������",
            "0103*�������",
            "0104*�������",
            "0105*�������",
            "0106*�������",
            "0107*�������",
            "0105  ·������",
            "0115  Ԫ����",
            "0202  ��̧ͷ",
            "0219  ������ʥ��",
            "0404  ��ʳ��",
            "0408  �𵮽� ",
            "0505*�����",
            "0606  ���ܽ� �ùý�",
            "0624  �����ѽ�",
            "0707  ��Ϧ���˽�",
            "0714  ���(�Ϸ�)",
            "0715  ������",
            "0730  �زؽ�",
            "0815*�����",
            "0909  ������",
            "1001  �����",
            "1117  �����ӷ�ʥ��",
            "1208  ���˽� ���������ɵ���",
            "1223  ��С��",
            "1229*���¶�ʮ��",
            "0100*��Ϧ"};
    //ĳ�µĵڼ������ڼ�; 5,6,7,8 ��ʾ������ 1,2,3,4 �����ڼ�
    String[] wFtv = new String[]{
            "0110  ���˽�",
            "0150  ���������",
            "0121  �ձ����˽�",
            "0520  ĸ�׽�",
            "0530  ȫ��������",
            "0630  ���׽�",
            "0716  ������",
            "0730  ��ū�۹�����",
            "0932  ���ʺ�ƽ��",
            "0940  �������˽� �����ͯ��",
            "1011  ����ס����",
            "1144  �ж���"};
    private Long length;//������������
    private int firstWeek;  //��������1�����ڼ�
    public static Element getCalendarDetail(Date date) throws ParseException {


        Calendar cal = Calendar.getInstance() ;
        cal.setTime(date);
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        String cacheKey=(year+"-"+month);
        SimpleCalendar lunarCalendarUtil=null;
        if(cache.containsKey(cacheKey)){
            lunarCalendarUtil=cache.get(cacheKey);
        }else {
            lunarCalendarUtil=new SimpleCalendar();
            lunarCalendarUtil.calendar(year, month);
            cache.put(cacheKey,lunarCalendarUtil);
        }

        return lunarCalendarUtil.getElements().get(cal.get(Calendar.DATE)-1);
    }

    public static Element getCalendarDetail(String date,String pattern) throws ParseException {
        SimpleDateFormat df2 = new SimpleDateFormat(pattern);
        return getCalendarDetail(df2.parse(date));
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public void calendar(int y, int m) throws ParseException {
        Date sDObj = null;
        Lunar lDObj = null;
        Boolean lL=null;
        Long lD2=null;
        Integer lY = null, lM=null, lD = 1, lX = 0, tmp1, tmp2, lM2, lY2=null, tmp3, dayglus, bsg, xs, xs1, fs, fs1, cs, cs1=null;
        String cY, cM, cD; //����,����,����
        Integer[] lDPOS = new Integer[3];
        Integer n = 0;
        Integer firstLM = 0;
        String dateString = y + "-" +(m+1) + "-" + 1;
        sDObj = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        this.length = solarDays(y, m);    //������������
        this.firstWeek = sDObj.getDay();    //��������1�����ڼ�

        ////////���� 1900��������Ϊ������(60����36)
        if (m < 2) cY = cyclical(y - 1900 + 36 - 1);
        else cY = cyclical(y - 1900 + 36);
        int term2 = sTerm(y, 2); //��������

        ////////���� 1900��1��С����ǰΪ ������(60����12)
        int firstNode = sTerm(y, m * 2);//���ص��¡��ڡ�Ϊ���տ�ʼ
        cM = cyclical((y - 1900) * 12 + m + 12);

        lM2 = (y - 1900) * 12 + m + 12;
        //����һ���� 1900/1/1 �������
        //1900/1/1�� 1970/1/1 ���25567��, 1900/1/1 ����Ϊ������(60����10)
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = df2.parse("" + y + "-" + (m+1) + "-" + 1 + " 00:00:00");

        long dayCyclical = date.getTime() / 86400000 + 25567 + 10;
        //// long dayCyclical =date.getTime() / 86400000 + 25567 + 10;

        for (int i = 0; i < this.length; i++) {
            if (lD > lX) {
                sDObj = new Date(y, m, i + 1);    //����һ������
                lDObj = new Lunar(sDObj);     //ũ��
                lY = lDObj.year;           //ũ����
                lM = lDObj.month;          //ũ����
                lD = lDObj.day;            //ũ����
                lL = lDObj.isLeap;         //ũ���Ƿ�����
                lX = lL ? leapDays(lY) : monthDays(lY, lM); //ũ���������һ��

                if (n == 0) firstLM = lM;
                lDPOS[n++] = i - lD + 1;
            }

            //�������������·ֵ�����, ������Ϊ��
            if (m == 1 && (i + 1) == term2) {
                cY = cyclical(y - 1900 + 36);
                lY2 = (y - 1900 + 36);
            }
            //����������, �ԡ��ڡ�Ϊ��
            if ((i + 1) == firstNode) {
                cM = cyclical((y - 1900) * 12 + m + 13);
                lM2 = (y - 1900) * 12 + m + 13;
            }
            //����
            cD = cyclical(dayCyclical + i);
            lD2 = (dayCyclical + i);
            Element element = new Element(y, m + 1, i + 1, (nStr1[(i + this.firstWeek) % 7]),
                    lY, lM, lD++, lL,
                    cY, cM, cD);
            element.setcDay(cDay(element.getlDay()));
            int paramterLy2=lY2==null?-1:(lY2 % 12);
            int paramterLm2=lM2==null?-1:lM2 % 12;
            long paramterLd2=lD2==null?-1:lD2 % 12;
            int paramterLy2b=lY2==null?-1:lY2 % 10;
            int paramterLy2c= (int) (lD2==null?-1:lD2 % 10);
            int paramterLld=lD==null?-1:lD - 1;
            element.setSgz5(CalConv2(paramterLy2, paramterLm2, (int) paramterLd2, paramterLy2b,paramterLy2c , lM,paramterLld , m + 1, cs1==null?-1:cs1));
            element.setSgz3(cyclical6(lM2 % 12, (int) ((lD2) % 12)));
            elements.add(element);


        }

        //����
        tmp1 = sTerm(y, m * 2) - 1;
        tmp2 = sTerm(y, m * 2 + 1) - 1;
        elements.get(tmp1).solarTerms = solarTerm[m * 2];
        elements.get(tmp2).solarTerms = solarTerm[m * 2 + 1];
        if (m == 3) elements.get(tmp1).color = "red"; //������ɫ

        Pattern p = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
        //��������
        for (String i : sFtv){
            Matcher matcher=p.matcher(i);
            if (matcher.matches()) {
                if(i.equals("1212  �����±������")){
                    int j=2;
                }
                if (Integer.valueOf(matcher.group(1)).intValue() == (m + 1)) {
                    elements.get(Integer.valueOf(matcher.group(2)) - 1).solarFestival +=matcher.group(4)+"";
                    if (matcher.group(3).equals('*'))   elements.get(Integer.valueOf(matcher.group(0)) - 1).color = "red";
                }
            }
        }

        p = Pattern.compile("^(\\d{2})(.{2})([\\s\\*])(.+)$");
        //ũ������
        for (String i  :  lFtv){
            Matcher matcher=p.matcher(i);
            if (matcher.matches()) {
                tmp1 = Integer.valueOf(matcher.group(1)) - firstLM;
                if (tmp1 == -11) tmp1 = 1;
                if (tmp1 >= 0 && tmp1 < n) {
                    tmp2 = lDPOS[tmp1] +Integer.valueOf(matcher.group(2)) - 1;
                    if (tmp2 >= 0 && tmp2 < this.length) {
                        elements.get(tmp2).lunarFestival += matcher.group(4);
                        if (matcher.group(3).equals("*")) elements.get(tmp2).color = "red";
                    }
                }
            }
        }

        //�����ֻ������3��4��
        if (m == 2 || m == 3) {
            Easter estDay = new Easter(y);
            if (m == estDay.m)
                elements.get(estDay.d - 1).solarFestival = elements.get(estDay.d - 1).solarFestival + " �����(Easter Sunday)";
        }


        //��ɫ������
        if ((this.firstWeek + 12) % 7 == 5)
            elements.get(12).solarFestival += "��ɫ������";

        //����
        //if (y == tY && m == tM) this[tD - 1].isToday = true;
    }
    //==============================���ع��� y��ĳm+1�µ�����
    public long solarDays(int y, int m) {
        if (m == 1)
            return(((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0)) ? 29 : 28);
        else
            return(solarMonth[m]);
    }
    //============================== �������� (y��,m+1��)
    public char cyclical6(int num, int num2) {
        if (num == 0) return(jcName0[num2]);
        if (num == 1) return(jcName1[num2]);
        if (num == 2) return(jcName2[num2]);
        if (num == 3) return(jcName3[num2]);
        if (num == 4) return(jcName4[num2]);
        if (num == 5) return(jcName5[num2]);
        if (num == 6) return(jcName6[num2]);
        if (num == 7) return(jcName7[num2]);
        if (num == 8) return(jcName8[num2]);
        if (num == 9) return(jcName9[num2]);
        if (num == 10) return(jcName10[num2]);
        if (num == 11) return(jcName11[num2]);
        return '0';
    }
    public String  CalConv2(int yy,int  mm,int dd,int y,int d,int m, int dt,int  nm,int nd) {
        int dy = d  + dd;
        if ((yy == 0 && dd == 6) || (yy == 6 && dd == 0) || (yy == 1 && dd == 7) || (yy == 7 && dd == 1) || (yy == 2 && dd == 8) || (yy == 8 && dd == 2) || (yy == 3 && dd == 9) || (yy == 9 && dd == 3) || (yy == 4 && dd == 10) || (yy == 10 && dd == 4) || (yy == 5 && dd == 11) || (yy == 11 && dd == 5)) {
            return "<FONT color=#0000A0>��ֵ���� ���²���</font>";
        }
        else if ((mm == 0 && dd == 6) || (mm == 6 && dd == 0) || (mm == 1 && dd == 7) || (mm == 7 && dd == 1) || (mm == 2 && dd == 8) || (mm == 8 && dd == 2) || (mm == 3 && dd == 9) || (mm == 9 && dd == 3) || (mm == 4 && dd == 10) || (mm == 10 && dd == 4) || (mm == 5 && dd == 11) || (mm == 11 && dd == 5)) {
            return "<FONT color=#0000A0>��ֵ���� ���²���</font>";
        }
        else if ((y == 0 && dy == 911) || (y == 1 && dy == 55) || (y == 2 && dy == 111) || (y == 3 && dy == 75) || (y == 4 && dy == 311) || (y == 5 && dy == 9) || (y == 6 && dy == 511) || (y == 7 && dy == 15) || (y == 8 && dy == 711) || (y == 9 && dy == 35)) {
            return "<FONT color=#0000A0>��ֵ��˷ ���²���</font>";
        }
        else if ((m == 1 && dt == 13) || (m == 2 && dt == 11) || (m == 3 && dt == 9) || (m == 4 && dt == 7) || (m == 5 && dt == 5) || (m == 6 && dt == 3) || (m == 7 && dt == 1) || (m == 7 && dt == 29) || (m == 8 && dt == 27) || (m == 9 && dt == 25) || (m == 10 && dt == 23) || (m == 11 && dt == 21) || (m == 12 && dt == 19)) {
            return "<FONT color=#0000A0>��ֵ�ʮ���� ���²���</font>";
        }
        else {
            return "0";
        }
    }
    //    public Date getUtcDate(String dateStr){
//	        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
//	        Date date = df2.parse("1900-01-06 02:05:00");
//	    }
    //============================== ���� offsenew Datet ���ظ�֧, 0=����
    public String cyclical(long num) {
        return(Gan[(int) (num % 10)] + Zhi[(int) (num % 12)]);
    }
    //======================  ��������
    public String cDay(int d) {
        String  s;

        switch (d) {
            case  10:
                s = "��ʮ";  break;
            case  20:
                s = "��ʮ";  break;
            case  30:
                s = "��ʮ";  break;
            default  :
                s = nStr2[Double.valueOf(Math.floor(d / 10)).intValue()];
                s += nStr1[d % 10];
        }
        return(s);
    }
    //===== ĳ��ĵ�n������Ϊ����(��0С������)
    public int sTerm(int  y,int  n) throws ParseException {
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = df2.parse("1900-01-06 02:05:00");
        Long utcTime2=date.getTime();
        BigDecimal time2=new BigDecimal(31556925974.7).multiply(new BigDecimal(y - 1900)).add(new BigDecimal( sTermInfo[n]).multiply(BigDecimal.valueOf(60000L)));
        BigDecimal time=time2.add(BigDecimal.valueOf(utcTime2));
        Date offDate = new Date(time.longValue());
        Calendar cal = Calendar.getInstance() ;
        cal.setTime(offDate);
        int utcDate=cal.get(Calendar.DATE);
        return utcDate;
    }
    //====================================== ����ũ�� y�����ĸ��� 1-12 , û�򷵻� 0
    public Long  leapMonth(int y) {
        long lm = lunarInfo[y - 1900] & 0xf;
        return(lm == 0xf ? 0 : lm);
    }
    //====================================== ����ũ�� y���������
    public Long lYearDays(int y) {
        long i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) sum += (lunarInfo[y - 1900] & i)!=0 ? 1 : 0;
        return(sum + leapDays(y));
    }

    //====================================== ����ũ�� y�����µ�����
    public int leapDays(int y) {
        if (leapMonth(y)!=0) return( (lunarInfo[y - 1899] & 0xf) == 0xf ? 30 : 29);
        else return 0;
    }
    //====================================== ����ũ�� y��m�µ�������
    private int monthDays(int y,int m) {
        return( (lunarInfo[y - 1900] & (0x10000 >> m))!=0 ? 30 : 29 );
    }

    public  class Lunar{
        private  int year;
        private boolean isLeap;
        private  int month;
        private  int day;
        public Lunar(Date objDate) throws ParseException {
            int i, leap = 0, temp = 0;
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            df2.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = df2.parse("" + objDate.getYear() + "-" + (objDate.getMonth()+1) + "-" + objDate.getDate() + " 00:00:00");
            SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            df2.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date3 = df2.parse("" + 1900 + "-" + 1 + "-" + 31 + " 00:00:00");
            int offset = (int)((date.getTime() -date3.getTime()) / 86400000);

            for (i = 1900; i < 2100 && offset > 0; i++) {
                temp = lYearDays(i).intValue();
                offset -= temp;
            }

            if (offset < 0) {
                offset += temp;
                i--;
            }

            this.year = i;

            leap = leapMonth(i).intValue(); //���ĸ���
            this.isLeap = false;

            for (i = 1; i < 13 && offset > 0; i++) {
                //����
                if (leap > 0 && i == (leap + 1) && this.isLeap == false) {
                    --i;
                    this.isLeap = true;
                    temp = leapDays(this.year);
                }
                else {
                    temp = monthDays(this.year, i);
                }

                //�������
                if (this.isLeap == true && i == (leap + 1)) this.isLeap = false;

                offset -= temp;
            }

            if (offset == 0 && leap > 0 && i == leap + 1)
                if (this.isLeap) {
                    this.isLeap = false;
                }
                else {
                    this.isLeap = true;
                    --i;
                }

            if (offset < 0) {
                offset += temp;
                --i;
            }

            this.month = i;
            this.day = offset + 1;
        }
    }

    public class Easter{

        public int m;
        public int d;
        public Easter(int y) throws ParseException {
            int term2 = sTerm(y, 5); //ȡ�ô�������
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            df2.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date dayTerm2 = df2.parse("" +y + "-" +2 + "-" + term2 + " 00:00:00");//ȡ�ô��ֵĹ������ڿؼ�(����һ��������3��)
            Lunar lDayTerm2 = new Lunar(dayTerm2); //ȡ��ȡ�ô���ũ��
            int lMlen=0;
            if (lDayTerm2.day < 15) //ȡ���¸���Բ���������
                lMlen = 15 - lDayTerm2.day;
            else
                lMlen = (lDayTerm2.isLeap ? leapDays(y) : monthDays(y, lDayTerm2.month)) - lDayTerm2.day + 15;

            //һ����� 1000*60*60*24 = 86400000 ����
            Date l15 = new Date(dayTerm2.getTime() + 86400000 * lMlen); //�����һ����ԲΪ��������
            Date dayEaster = new Date(l15.getTime() + 86400000 * ( 7 - l15.getDay() )); //����¸�����

            this.m = dayEaster.getMonth();
            this.d = dayEaster.getDate();
        }
    }
    public static class Element{
        public int sYear;
        public int  sMonth;
        public int sDay;
        public char  week;
        public int lYear;
        public int  lMonth;
        public int  lDay;
        public boolean  isLeap;
        public String  cYear;
        public String cMonth;
        public String cDay;
        public String color;
        public boolean isToday=false;
        public String lunarFestival;
        public String solarFestival;
        public String solarTerms;
        public String sgz5;
        public char sgz3;
        public Element(int sYear,int  sMonth, int sDay,char  week,int lYear,int  lMonth,int  lDay,boolean  isLeap,String  cYear, String cMonth, String cDay) {

            this.isToday = false;
            //���
            this.sYear = sYear;   //��Ԫ��4λ����
            this.sMonth = sMonth;  //��Ԫ������
            this.sDay = sDay;    //��Ԫ������
            this.week = week;    //����, 1������
            //ũ��
            this.lYear = lYear;   //��Ԫ��4λ����
            this.lMonth = lMonth;  //ũ��������
            this.lDay = lDay;    //ũ��������
            this.isLeap = isLeap;  //�Ƿ�Ϊũ������?
            //����
            this.cYear = cYear;   //����, 2������
            this.cMonth = cMonth;  //����, 2������
            this.cDay = cDay;    //����, 2������

            this.color = "";

            this.lunarFestival = ""; //ũ������
            this.solarFestival = ""; //��������
            this.solarTerms = ""; //����
        }

        public String getSgz5() {
            return sgz5;
        }

        public void setSgz5(String sgz5) {
            this.sgz5 = sgz5;
        }

        public char getSgz3() {
            return sgz3;
        }

        public void setSgz3(char sgz3) {
            this.sgz3 = sgz3;
        }

        public int getsYear() {
            return sYear;
        }

        public void setsYear(int sYear) {
            this.sYear = sYear;
        }

        public int getsMonth() {
            return sMonth;
        }

        public void setsMonth(int sMonth) {
            this.sMonth = sMonth;
        }

        public int getsDay() {
            return sDay;
        }

        public void setsDay(int sDay) {
            this.sDay = sDay;
        }

        public char getWeek() {
            return week;
        }

        public void setWeek(char week) {
            this.week = week;
        }

        public int getlYear() {
            return lYear;
        }

        public void setlYear(int lYear) {
            this.lYear = lYear;
        }

        public int getlMonth() {
            return lMonth;
        }

        public void setlMonth(int lMonth) {
            this.lMonth = lMonth;
        }

        public int getlDay() {
            return lDay;
        }

        public void setlDay(int lDay) {
            this.lDay = lDay;
        }

        public boolean isLeap() {
            return isLeap;
        }

        public void setLeap(boolean leap) {
            isLeap = leap;
        }

        public String getcYear() {
            return cYear;
        }

        public void setcYear(String cYear) {
            this.cYear = cYear;
        }

        public String getcMonth() {
            return cMonth;
        }

        public void setcMonth(String cMonth) {
            this.cMonth = cMonth;
        }

        public String getcDay() {
            return cDay;
        }

        public void setcDay(String cDay) {
            this.cDay = cDay;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isToday() {
            return isToday;
        }

        public void setToday(boolean today) {
            isToday = today;
        }

        public String getLunarFestival() {
            return lunarFestival;
        }

        public void setLunarFestival(String lunarFestival) {
            this.lunarFestival = lunarFestival;
        }

        public String getSolarFestival() {
            return solarFestival;
        }

        public void setSolarFestival(String solarFestival) {
            this.solarFestival = solarFestival;
        }

        public String getSolarTerms() {
            return solarTerms;
        }

        public void setSolarTerms(String solarTerms) {
            this.solarTerms = solarTerms;
        }

		@Override
		public String toString() {
			return "Element [sYear=" + sYear + ", sMonth=" + sMonth + ", sDay=" + sDay + ", week=" + week + ", lYear="
					+ lYear + ", lMonth=" + lMonth + ", lDay=" + lDay + ", isLeap=" + isLeap + ", cYear=" + cYear
					+ ", cMonth=" + cMonth + ", cDay=" + cDay + ", color=" + color + ", isToday=" + isToday
					+ ", lunarFestival=" + lunarFestival + ", solarFestival=" + solarFestival + ", solarTerms="
					+ solarTerms + ", sgz5=" + sgz5 + ", sgz3=" + sgz3 + "]";
		}
        
    }
}
