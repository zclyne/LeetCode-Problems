// 方法1：直接循环

class Solution {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = i + 1;
            if (num % 3 == 0 && num % 5 == 0) {
                result.add("FizzBuzz");
            } else if (num % 3 == 0) {
                result.add("Fizz");
            } else if (num % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(num));
            }
        }
        return result;
    }
}

// 方法2：不用取模，用两个变量记录是否为3或5的倍数

public class Solution2 {
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<String>(n);
        for(int i=1,fizz=0,buzz=0;i<=n ;i++){
            fizz++;
            buzz++;
            if(fizz==3 && buzz==5){
                ret.add("FizzBuzz");
                fizz=0;
                buzz=0;
            }else if(fizz==3){
                ret.add("Fizz");
                fizz=0;
            }else if(buzz==5){
                ret.add("Buzz");
                buzz=0;
            }else{
                ret.add(String.valueOf(i));
            }
        } 
        return ret;
    }
}