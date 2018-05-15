package com.socket.dodgem;




public class Json {
	int win;
	String usrname;
	int lose;



	public int compareTo(Json x) {
        int result = 0;
        //按照胜场降序
        result = - (this.win-x.win);
        if(0==result){//如果胜场相同 就按照输场升序排列
            result =this.lose-x.lose;
            if(0==result){//如果输场相同 用户名
                result = - this.usrname.compareTo(x.usrname);
            }
        }
        return result;
       }




/**
 * 直接插入排序
 *
 */
	public static void DirectInsertSort(Json[] x){
	    for (int i = 0; i < x.length - 1; i++) {
	        Json temp = x[i + 1];
	        if (x[i].compareTo(x[i + 1])<0) {
	            int j = i;
	            while(j >= 0 && (x[j].compareTo(temp)<0 )){
	                x[j + 1] = x[j];
	                j--;
	            }
	            x[j + 1] = temp;
	        }
	    }
	}


}
