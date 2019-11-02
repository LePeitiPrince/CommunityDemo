package spring.adog.dto;

public class PageUtil {

    private static Integer totalPage;

    public static Integer getTotalPage(Integer totalCount, Integer size){
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        return totalPage;
    }

    public static Integer getPage(Integer page,Integer totalPage){
        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        return page;
    }
}
