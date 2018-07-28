package com.whut.epSystem.util;

public class PathUtil {
    private static String seperator=System.getProperty("file.separator");

    /**
     * 返回项目图片根路径
     * @return
     */
    public static String getImgBasePath(){
        String os=System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath="D:/ep/image/";
        }else {
            basePath="/home/ep/image/";
        }
        basePath=basePath.replace("/",seperator);
        return basePath;
    }

    /**
     * 依据不同的业务需求，返回项目图片的子路径
     * @param listId
     * @return
     */
    public static String getExercisesListPath(long listId){
        String imagePath="upload/item/shop"+listId+"/";
        return imagePath.replace("/",seperator);
    }

    public static String getQuestionPath(long listId,long queId){
        String imagePath="upload/item/shop"+listId+"/"+queId+"/";
        return imagePath.replace("/",seperator);
    }
}
