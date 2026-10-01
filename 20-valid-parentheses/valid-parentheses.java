class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();
        for(int i = 0;i<n;i++){
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                st.push(s.charAt(i));
            
            else if(st.empty())
            
                return false;
            
            else{
                char c = st.peek();
                if((s.charAt(i) == ')' && c == '(') || (s.charAt(i) == '}' && c == '{') || (s.charAt(i) == ']' && c == '['))
                    st.pop();
                else
                    return false;
            }
  
        }

        if(st.empty())
            return true;
        else
            return false;
    }
}