//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
class Solution {
//    void reverse(int[] nums, int start, int end) {
//        int len = end-start+1, temp;
//        for (int i=start; i < start+len/2; i++) {
//            temp = nums[i];
//            nums[i] = nums[end+start-i];
//            nums[end+start-i] = temp;
//        }
//    }
//    public void rotate(int[] nums, int k) {
//        // [1,2,3,4,5,6,7]
//        // [4,3,2,1,7,6,5]
//        // [5,6,7,1,2,3,4]
//        int len = nums.length;
//        reverse(nums, 0, len-1);
//        reverse(nums, 0, k-1);
//        reverse(nums, k, len-1);
//    }
    public void rotate(int[] nums, int k) {
        // [1,2,3,4,5,6,7]
        // [4,3,2,1,7,6,5]
        // [5,6,7,1,2,3,4]
        int len = nums.length, index = 0;
        k=k%len;
        if (len == 1 || k == 0) return;
        int[] res = new int[len];
        for (int i=len-k; i<len; i++) res[index++] = nums[i];
        for (int i=0; i<len-k; i++) res[index++] = nums[i];
        nums=res;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)
void main() {
    Solution solution = new Solution();
    {
        String[] rawInputs = new String[] { "[1,2,3,4,5,6,7]", "3" };
        String expected = "";
        runCase(solution, "rotate", rawInputs, expected);
    }
    {
        String[] rawInputs = new String[] { "[-1,-100,3,99]", "2" };
        String expected = "[3,99,-1,-100]";
        runCase(solution, "rotate", rawInputs, expected);
    }
}

static void runCase(Object solution,String methodName,String[] rawInputs,String expected){try{java.lang.reflect.Method target=null;for(java.lang.reflect.Method m:solution.getClass().getDeclaredMethods())if(m.getName().equals(methodName)){target=m;break;}if(target==null)throw new RuntimeException("Method not found: "+methodName);Class<?>[] ptypes=target.getParameterTypes();if(rawInputs.length<ptypes.length)throw new RuntimeException("Not enough input lines. need="+ptypes.length+" got="+rawInputs.length);Object[] args=new Object[ptypes.length];for(int i=0;i<ptypes.length;i++)args[i]=parseValue(rawInputs[i],ptypes[i]);Object out=target.invoke(solution,args);System.out.println("input:");for(String s:rawInputs)System.out.println(s);System.out.println("output: "+toPrettyString(out));System.out.println("expected output: "+expected);System.out.println();}catch(Exception e){e.printStackTrace();System.out.println();}}
static String toPrettyString(Object v){if(v==null)return"null";Class<?> c=v.getClass();if(!c.isArray())return String.valueOf(v);int n=java.lang.reflect.Array.getLength(v);StringBuilder sb=new StringBuilder();sb.append("[");for(int i=0;i<n;i++){if(i>0)sb.append(", ");Object e=java.lang.reflect.Array.get(v,i);sb.append(toPrettyString(e));}sb.append("]");return sb.toString();}
static Object parseValue(String s,Class<?> type){s=s==null?"":s.trim();if(s.equals("null"))return null;if(type==String.class)return unquote(s);if(type==char.class||type==Character.class){String u=unquote(s);if(u.length()!=1)throw new RuntimeException("char expected, got: "+s);return u.charAt(0);}if(type==int.class||type==Integer.class)return Integer.parseInt(s);if(type==long.class||type==Long.class)return Long.parseLong(s);if(type==double.class||type==Double.class)return Double.parseDouble(s);if(type==float.class||type==Float.class)return Float.parseFloat(s);if(type==boolean.class||type==Boolean.class)return Boolean.parseBoolean(s);if(type==short.class||type==Short.class)return Short.parseShort(s);if(type==byte.class||type==Byte.class)return Byte.parseByte(s);if(type.isArray()){Class<?> comp=type.getComponentType();java.util.List<String> items=splitTopLevelArrayItems(s);Object arr=java.lang.reflect.Array.newInstance(comp,items.size());for(int i=0;i<items.size();i++){Object elem=parseValue(items.get(i),comp);java.lang.reflect.Array.set(arr,i,elem);}return arr;}if(java.util.List.class.isAssignableFrom(type)){java.util.List<String> items=splitTopLevelArrayItems(s);java.util.ArrayList<Object> list=new java.util.ArrayList<>();for(String it:items)list.add(parseJsonLike(it));return list;}if(java.util.Map.class.isAssignableFrom(type))return parseMapLike(s);return parseJsonLike(s);}
static String unquote(String s){s=s.trim();if((s.startsWith("\"")&&s.endsWith("\""))||(s.startsWith("'")&&s.endsWith("'")))s=s.substring(1,s.length()-1);return s;}
static Object parseJsonLike(String s){s=s.trim();if(s.equals("null"))return null;if(s.equals("true")||s.equals("false"))return Boolean.parseBoolean(s);if(s.startsWith("\"")||s.startsWith("'"))return unquote(s);if(s.startsWith("[")){java.util.List<String> items=splitTopLevelArrayItems(s);java.util.ArrayList<Object> list=new java.util.ArrayList<>();for(String it:items)list.add(parseJsonLike(it));return list;}if(s.startsWith("{"))return parseMapLike(s);if(s.matches("[-+]?\\d+")){try{return Integer.parseInt(s);}catch(Exception ignore){}try{return Long.parseLong(s);}catch(Exception ignore){}}if(s.matches("[-+]?\\d*\\.\\d+([eE][-+]?\\d+)?")||s.matches("[-+]?\\d+([eE][-+]?\\d+)?"))try{return Double.parseDouble(s);}catch(Exception ignore){}return s;}
static java.util.List<String> splitTopLevelArrayItems(String s){s=s.trim();if(!s.startsWith("[")||!s.endsWith("]")){java.util.ArrayList<String> one=new java.util.ArrayList<>();if(!s.isEmpty())one.add(s);return one;}String body=s.substring(1,s.length()-1).trim();java.util.ArrayList<String> out=new java.util.ArrayList<>();if(body.isEmpty())return out;int depth=0;boolean inStr=false;char strCh=0;int start=0;for(int i=0;i<body.length();i++){char ch=body.charAt(i);if(inStr){if(ch==strCh&&body.charAt(Math.max(i-1,0))!='\\')inStr=false;continue;}if(ch=='"'||ch=='\''){inStr=true;strCh=ch;continue;}if(ch=='['||ch=='{'||ch=='(')depth++;else if(ch==']'||ch=='}'||ch==')')depth--;else if(ch==','&&depth==0){out.add(body.substring(start,i).trim());start=i+1;}}out.add(body.substring(start).trim());return out;}
static java.util.Map<String,Object> parseMapLike(String s){s=s.trim();if(!s.startsWith("{")||!s.endsWith("}"))throw new RuntimeException("map expected: "+s);String body=s.substring(1,s.length()-1).trim();java.util.LinkedHashMap<String,Object> map=new java.util.LinkedHashMap<>();if(body.isEmpty())return map;java.util.List<String> parts=splitTopLevelComma(body);for(String kv:parts){int colon=findTopLevelColon(kv);if(colon<0)throw new RuntimeException("bad map entry: "+kv);String k=kv.substring(0,colon).trim();String v=kv.substring(colon+1).trim();k=unquote(k);map.put(k,parseJsonLike(v));}return map;}
static java.util.List<String> splitTopLevelComma(String body){java.util.ArrayList<String> out=new java.util.ArrayList<>();int depth=0;boolean inStr=false;char strCh=0;int start=0;for(int i=0;i<body.length();i++){char ch=body.charAt(i);if(inStr){if(ch==strCh&&body.charAt(Math.max(i-1,0))!='\\')inStr=false;continue;}if(ch=='"'||ch=='\''){inStr=true;strCh=ch;continue;}if(ch=='['||ch=='{'||ch=='(')depth++;else if(ch==']'||ch=='}'||ch==')')depth--;else if(ch==','&&depth==0){out.add(body.substring(start,i).trim());start=i+1;}}out.add(body.substring(start).trim());return out;}
static int findTopLevelColon(String s){int depth=0;boolean inStr=false;char strCh=0;for(int i=0;i<s.length();i++){char ch=s.charAt(i);if(inStr){if(ch==strCh&&s.charAt(Math.max(i-1,0))!='\\')inStr=false;continue;}if(ch=='"'||ch=='\''){inStr=true;strCh=ch;continue;}if(ch=='['||ch=='{'||ch=='(')depth++;else if(ch==']'||ch=='}'||ch==')')depth--;else if(ch==':'&&depth==0)return i;}return -1;}