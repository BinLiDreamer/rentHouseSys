package tool;

import java.util.Scanner;


public class ReadTool {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println(readInt(1,3));
    }

    /**
     * 功能：接收用户只能输入y或n
     * @return 返回y或n（忽略大小写）
     */
    public static String selectYorN(){
        while (true){
            String str = readKeyBoard(1,false);
            if("Y".equalsIgnoreCase(str)||"N".equalsIgnoreCase(str)){
                return str;
            }else {
                System.out.print("你的输入有误，请重新输入(y/n):");
            }
        }
    }

    /**
     * 功能：接收用户输入low-high（包括）的整数
     * @param low 用户输入的下限（含）
     * @param high 用户输入的上限（含）
     * @return 返回用户输入low-high（包括）的整数
     */
    public static int readInt(int low,int high){
        int retNum;
        while(true){
            String str = readKeyBoard(10,false);
            try {
                retNum = Integer.parseInt(str);
                if(retNum>high||retNum<low){
                    throw new NumberFormatException();
                }
                break;
            }catch (NumberFormatException e){
                System.out.print("你需要输入在"+low+"-"+high+"(含)之间的整数:");
                continue;
            }
        }
        return retNum;
    }

    /**
     * 功能：接收用户输入整数，如果直接回车则返回参数默认值
     * @param defaultNum 默认参数，如果用户直接输入回车则返回此数
     * @return 返回用户输入的整数或者返回默认参数
     */
    public static int readInt(int defaultNum){
        int retNum;
        while (true){
            String str = readKeyBoard(10,true);
            if(str.length()==0){
                return defaultNum;
            }
            try {
                retNum = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e){
                System.out.print("你输入的不是数字，请重新输入:");
                continue;
            }
        }
        return retNum;
    }

    /**
     * 功能：用于接收用户输入整数并返回
     * @return 返回用户输入的整数
     */
    public static int readInt(){
        int retNum;
        while (true){
            String str=readKeyBoard(10,false);
            try {
                retNum = Integer.parseInt(str);
                break;
            }catch (NumberFormatException e){
                System.out.print("你输入的不是数字，请重新输入:");
                continue;
            }
        }
        return retNum;
    }

    /**
     * 功能：用于返回用户输入的字符
     * @return 返回用户输入的字符
     */
    public static char readChar(){
        String str=readKeyBoard(1,false);
        return str.charAt(0);
    }

    /**
     * 功能：返回用户输入的字符串，限制在limit个长度之内，直接回车则返回默认值
     * @param limit 限定用户输入的字符串长度
     * @param defaultStr 用户输入回车则返回该默认字符串
     * @return 返回用户输入的字符串或者默认字符串
     */
    public static String readString(int limit,String defaultStr){
        String str = readKeyBoard(limit,true);
        return str.length()==0?defaultStr:str;
    }

    /**
     * 功能：返回用户输入的限定的limit长度范围内的字符串，不能直接回车
     * @param limit 接收用户输入的字符串长度
     * @return  返回用户输入的在limit个长度的字符串
     */
    public static String readString(int limit){
        return readKeyBoard(limit,false);
    }

    /**
     * 功能：用于返回用户输入的字符串或者空串
     * @param limit 限定用户输入字符串长度
     * @param isRetBlack 确定用户是否能直接回车
     * @return  返回用户输入的字符串或者空串
     */
    private static String readKeyBoard(int limit,boolean isRetBlack){
        String str = "";
        while (scanner.hasNextLine()){
            str = scanner.nextLine();
            if(str.length()>limit){
                System.out.print("输入的长度超过了"+limit+",请重新输入:");
                continue;
            }
            if(!isRetBlack&&str.length()==0){
                continue;
            }
            break;
        }
        return str;
    }
}
