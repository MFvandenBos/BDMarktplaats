package frontend.console;

public class MenuBuilder {

    public String createMenu(String[] options){
        StringBuilder out = new StringBuilder("");
        for(int i =0; i < options.length; i++){
            out.append((i+1)).append(": ").append(options[i]).append(System.lineSeparator());
        }
        return out.toString();
    }

    public String createBorderedOutput(String borderStyleH, String borderStyleV, String ... texts){
        int linelenght = 70;
        StringBuilder out = new StringBuilder(borderline(linelenght, borderStyleH));
        for (String text: texts){
            if(text.length()>66){
                String[] x = text.split(" ");
                StringBuilder internal = new StringBuilder("");
                for(int i = 0; i<x.length; i++){
                    if((internal.length()+x[i].length() )<= 66) {
                        internal.append(x[i]).append(" ");
                    }else {
                        out.append(messageline(internal.toString(), 70, borderStyleV));
                        internal.setLength(0);
                        internal.append(x[i]).append(" ");
                    }
                }
                out.append(messageline(internal.toString(), 70, borderStyleV));
            }else{
                out.append(messageline(text, 70, borderStyleV));
            }
            out.append(messageline("",70,borderStyleV));
        }
        out.append(borderline(70,borderStyleH));
        return out.toString();
    }

    public String createMenuHeader(String ... messages){
        int tempint = findLenghtLongestString(messages);
        int mwidth = 25 < tempint+10 ? tempint+10 : 25;

        StringBuilder full = new StringBuilder();
        if(messages.length> 0){
            full.append(starline(mwidth));
            for (String message : messages) {
                full.append(messageline(message ,mwidth, "*"));
            }
        }
        full.append(starline(mwidth));
        return full.toString();
    }

    private int findLenghtLongestString(String[] strings){
        int out = 0;
        for (String d: strings) {
            if(d.length()>out){
                out = d.length();
            }
        }
        return out;
    }

    private String starline(int linelenght){
        return borderline(linelenght, "*");
//        StringBuilder line = new StringBuilder("");
//        for (int j =0; j<linelenght; j++ ){
//            line.append("*");
//        }
//        line.append(System.lineSeparator());
//        return  line.toString();
    }

    private String messageline(String message, int linelenght, String border){
        if(border.length()>2){
            border = "x";
        }
        int el = linelenght-border.length()-message.length();

        StringBuilder line = new StringBuilder(border);
        int whitelenght = (el%2 == 0) ? (el)/2 : (el)/2+1;
        for(int j=0; j<whitelenght; j++){
            line.append(" ");
        }
        line.append(message);
        whitelenght = (((el)%2 == 0)) ? whitelenght : whitelenght - 1;
        for(int j=0; j<whitelenght; j++){
            line.append(" ");
        }
        line.append(border);
        line.append(System.lineSeparator());
        return line.toString();
    }

    private String borderline(int linelenght, String border){
        if(linelenght%border.length() == 0){
            linelenght = linelenght/border.length()+1;
        }else{
            linelenght = linelenght/border.length()+2;
        }

        StringBuilder line = new StringBuilder("");
        for (int j =0; j<linelenght; j++ ){
            line.append(border);
        }
        line.append(System.lineSeparator());
        return  line.toString();
    }
}
