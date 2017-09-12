import javax.swing.plaf.synth.SynthTextAreaUI;

/**
 * Created by DELL on 7/14/2017.
 */


public class MayTinh {
    static String tinh(String str) {
        String str1 = "";
        String str2 = "";
        String str3 = "";
        int check1 = 0, check2 = 0, check = 0;
        int i, j, k;
        int temp1 = 0, temp2 = 0;
        double kq = 0.0;

        char[] ch = str.toCharArray();
        //-------------------------de quy
        for (i = 0; i < ch.length; i++) {
            if (ch[i] == '*' || ch[i] == '/' || ch[i] == '+' || ch[i] == '-') {
                check1 = 1;
                break;
            }
            /*if (ch[i] == '-') {
                for (l = i; l < ch.length; l++) {
                    if (ch[l] == '*' || ch[l] == '/' || ch[l] == '+' || ch[l] == '-') {
                        check1 = 1;
                        break;
                    }
                    break;
                }
            }*/
        }

        if (check1 != 1) {
            return str;
        }

        //---------------thuc hien phep tinh
        //---nhan & chia
        for (i = 0; i < ch.length; i++) {
            if (ch[i] == '*' || ch[i] == '/') {
                for (int a = i - 1; a >= 0; a--)
                    if (ch[a] == '+' || ch[a] == '-') {
                        check = 1;
                        break;
                    }
                break;
            }
        }

        for (i = 0; i < ch.length; i++) {
            if (ch[i] == '*' || ch[i] == '/') {
                //------------so thu nhat
                for (j = i - 1; j >= 0; j--) {
                    if (check == 0) {
                        str1 = str1.copyValueOf(ch, 0, i);
                        temp1 = -1;
                        break;
                    }
                    if (check == 1) {
                        if (ch[j] == '*' || ch[i] == '/' || ch[i] == '+' || ch[i] == '-') {
                            str1 = str1.copyValueOf(ch, j + 1, i - j - 1);
                            temp1 = j;
                            break;
                        }
                    }
                }
                double num1 = Double.parseDouble(str1);
                //---------------- so thu hai
                for (k = i + 1; k < ch.length; k++) {
                    if (ch[k] == '*' || ch[k] == '/' || ch[k] == '+' || ch[k] == '-') {
                        check2 = 1;
                        break;
                    }
                }
                if (check2 == 0) {
                    str2 = str2.copyValueOf(ch, i + 1, ch.length - i - 1);
                    temp2 = ch.length;
                }
                if (check2 == 1) {
                    str2 = str2.copyValueOf(ch, i + 1, k - i - 1);
                    temp2 = k;
                }
                double num2 = Double.parseDouble(str2);
                // thuc hien phep tinh
                int check3 = 0;
                if (ch[i] == '*') kq = num1 * num2;
                else if (ch[i] == '/' && num2 != 0) kq = num1 / num2;
                else check3 = 1;
                if (check3 == 1) {
                    str = "Bieu thuc nhap vao sai!";
                    break;
                }
                String str4 = String.valueOf(kq);
                str3 = str3.copyValueOf(ch, temp1 + 1, temp2 - temp1 - 1);
                str = str.replace(str3, str4);
                return tinh(str);
            }
        }
//---------------cong & tru
        for (i = 0; i < ch.length; i++) {
            if (ch[i] == '+' || ch[i] == '-') {
                str1 = str1.copyValueOf(ch, 0, i);
                temp1 = -1;
                double num1 = Double.parseDouble(str1);
                //so thu hai
                for (k = i + 1; k < ch.length; k++) {
                    if (ch[k] == '*' || ch[k] == '/' || ch[k] == '+' || ch[k] == '-') {
                        check2 = 1;
                        break;
                    }
                }
                if (check2 == 0) {
                    str2 = str2.copyValueOf(ch, i + 1, ch.length - i - 1);
                    temp2 = ch.length;
                }
                if (check2 == 1) {
                    str2 = str2.copyValueOf(ch, i + 1, k - i - 1);
                    temp2 = k;
                }
                double num2 = Double.parseDouble(str2);
                ///tinh cong tru
                if (ch[i] == '+') kq = num1 + num2;
                else kq = num1 - num2;

                String str4 = String.valueOf(kq);
                str3 = str3.copyValueOf(ch, temp1 + 1, temp2 - temp1 - 1);
                str = str.replace(str3, str4);
                return tinh(str);
            }
        }
        return str;
    }

    static String dauNgoac(String str) {
        char[] ch = str.toCharArray();
        String str1 = "";
        String str2 = "";
        int check = 0;
        int i, j;
        for (i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                check = 1;
                break;
            }
        }
        if (check == 0) {
            str = tinh(str);
            return str;
        }

        for (i = 0; i < ch.length; i++) {
            if (ch[i] == ')') {
                for (j = i - 1; j >= 0; j--) {
                    if (ch[j] == '(') {
                        str1 = str.copyValueOf(ch, j + 1, i - j - 1);


                        str1 = tinh(str1);
                        str2 = str.copyValueOf(ch, j, i - j + 1);
                        str = str.replace(str2, str1);
                        break;
                    }
                }
                break;
            }
        }
        return dauNgoac(str);
    }


    public static void main(String args[]) {
        String str = "22*3+1";
        System.out.println(dauNgoac(str));
         str = "10/0";
        System.out.println(dauNgoac(str));
        //System.out.print(kq);
    }
}
