package com.tsstu.common.util;
import java.util.Random;

/**
 * 邀请码生成器，算法原理：<br/>
 * 1) 获取id: 1127738 <br/>
 * 2) 使用自定义进制转为：gpm6 <br/>
 * 3) 转为字符串，并在后面加'o'字符：gpm6o <br/>
 * 4）在后面随机产生若干个随机数字字符：gpm6o7 <br/>
 * 转为自定义进制后就不会出现o这个字符，然后在后面加个'o'，这样就能确定唯一性。最后在后面产生一些随机字符进行补全。<br/>
 * @author jiayu.qiu
 */
public class ShareCodeUtil {

    /**
     * 邀请码类型
     * @author liwei
     */
    public interface CodeType {
    	// 会员邀请码
    	public static final int MEMBER = 0;
    	// 代理会员邀请码
    	public static final int AGENT_MEMBER = 1;
    	// 经纪人邀请码
    	public static final int BROKER = 2;
    	// 普通客户分享码
    	public static final int CUSTOMER = 3;
    }

    /**
     * 根据ID生成六位随机码
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id, char b, int s, char[] r) {
        char[] buf=new char[32];
        int binLen = r.length;
        int charPos=32;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(b);
            Random rnd=new Random();
            for(int i=1; i < s - str.length(); i++) {
            sb.append(r[rnd.nextInt(binLen)]);
            }
            str+=sb.toString();
        }
        return str;
    }

    public static long codeToId(String code, char b, int binLen, char[] r) {
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }
    
    public static String generateShareCode(int id, int type) {
    	String code = "";
    	if (type == CodeType.MEMBER) {
    		char[] r =new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'm', 'n', '6', 'g', 'h'};
    		code = ShareCodeUtil.toSerialCode(id, 'a', 4, r);
    	} else if (type == CodeType.AGENT_MEMBER) {
    		char[] r =new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'm', 'n', '6', 'g', 'h'};
    		code = ShareCodeUtil.toSerialCode(id, 'b', 5, r);
    	} else if (type == CodeType.BROKER) {
    		char[] r =new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'a', '7', 'p', '5', 'i', 'k', '3', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'm', 'n', '6', 'g', 'h'};
    		code = ShareCodeUtil.toSerialCode(id, 'c', 6, r);
    	} else {
    		char[] r =new char[]{'q', 'w', 'e', '8', 's', '2', 'a', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'm', 'n', '6', 'g', 'h'};
    		code = ShareCodeUtil.toSerialCode(id, 'd', 7, r);
    	}
    	return code.toUpperCase();
    	
    }
    
    public static void main(String[] args) {
		System.out.println(generateShareCode(8001, CodeType.MEMBER));
		System.out.println(generateShareCode(80001, CodeType.AGENT_MEMBER)); 
		System.out.println(generateShareCode(800001, CodeType.BROKER));
		System.out.println(generateShareCode(8000001, CodeType.CUSTOMER));
		
	}
  
}