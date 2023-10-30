public class  ReverseString  {

    public static void main (String[] args) {
    assert args.length > 0;
         reverseString(args[0], args[0].length());

    }

    public static void  reverseString (String a, int i){

        if (i<=0){ 
            System.out.print(" ");
        }
        else{
            System.out.print(a.charAt(i-1));
            reverseString(a, --i);
        }

    }

    }

