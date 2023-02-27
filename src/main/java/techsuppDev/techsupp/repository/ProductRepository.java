package techsuppDev.techsupp.repository;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Product;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final EntityManager em;


    public Object findOne(Long id) {
        String sql = " " +
                "select " +
                " " +
                "from Product where id = "+
                id;
        Query nativeQuery = em.createNativeQuery(sql, "ProductMapping");
        Object singleProduct = nativeQuery.getSingleResult();
        return singleProduct;
    }

    public List<Product> findFiveProduct(int orderNumber, String keyword) {

        String sql = " " +
                "select * from " +
                "(select * from Product where period is not Null order by id desc)notNull ";
        String limitSql =
                "limit " +
                orderNumber +
                ", 5;" ;

        String keywordSql = "";

        if (keyword.equals("null") || keyword.equals("")) {
            sql = sql + limitSql;
        } else {
            keywordSql = "where product_name like '%" +
                    keyword +
                    "%' ";
            sql = sql + keywordSql + limitSql;
        }

        Query nativeQuery = em.createNativeQuery(sql, "ProductMapping");
        List<Product> fiveProduct = nativeQuery.getResultList();

        return fiveProduct;
    }




//    모든 데이터 가져오는 것
//    현재는 사용 x
    public List<Product> findAll() {
        return em.createQuery("select i from Product i", Product.class)
                .getResultList();
    }


    public Object JsonPagingCount(int pagingNumber, String keyword) {
        String sql = " " +
                "select count(*) from ";
        String noKeywordSql =
                " (select * from Product where period is not null limit " +
                pagingNumber +
                " , 50)noNullData;";

        String keywordSql = "(select * from Product where period is not null and product_name like '%" +
                keyword +
                "%' " +
                "limit " +
                pagingNumber +
                ", 50)searchData;";

        if (keyword.equals("null") || keyword.equals("")) {
            sql = sql + noKeywordSql;
        } else {
            sql = sql + keywordSql;
        }

        Query nativeQuery = em.createNativeQuery(sql);
        Object rowNum = nativeQuery.getSingleResult();
        System.out.println(rowNum);
        return rowNum;
    }




//    테스트 데이터 자동 생성
    public Object insertTestData() {
        System.out.println("===========");
        System.out.println("repository");
        String sql = " " +
                "insert into product " +
                "(seq_id, product_name, information, total_price, invest_price, period, product_status, create_date, click_count) values ";
        String middleSql = "(";
        String endSql = ")";
        
        String seqid = "imageFile";
        String productName = "testProduct";
        String information = "testInformation";
        String totalPrice;
        String investPrice;
        String cast = "cast('";
        String period;
        String periodUnderbar = "-";
        String periodEnd = " as datetime')";

        String productStatus;
        String createDate;
        String createDateEnd = " as datetime')";
        String clickCount;

//        date random
        Random random = new Random();

        int randomTotalPrice = random.nextInt(999999) + 100000;
        int randomInvestPrice = randomTotalPrice / 10;

        int year, month, day, hour, minute, second, createYear, createMonth, createDay;
        String sYear,sMonth,sDate,sDay;
        year = (int) Math.random() * 20 + 2023;
        month = (int) Math.random() * 2 + 11;
        day = (int) Math.random() * 17 + 11;
        hour = (int) Math.random() * 14 + 11;
        minute = (int) Math.random() * 49 + 11;
        second = (int) Math.random() * 49 + 11;
        createYear = year - 1;
        createMonth = month -1;
        createDay = day - 1;


//        status
        String[] statusArr = {"investing", "investClose", "productManufacture", "productComplete", "delivering", "deliverd", "success", "fail"};
        String statusInput = statusArr[random.nextInt(8) + 1 - 1].toString();

        int clickCountRandom = random.nextInt(50);
//
//
//
//
        int qwrandomTotalPrice = random.nextInt(999999) + 100000;
        int qwrandomInvestPrice = randomTotalPrice / 10;

        int qwyear, qwmonth, qwday, qwhour, qwminute, qwsecond, qwcreateYear, qwcreateMonth, qwcreateDay;
        String qwsYear, qwsMonth, qwsDate,qwsDay;
        qwyear = (int) Math.random() * 20 + 2023;
        qwmonth = (int) Math.random() * 2 + 11;
        qwday = (int) Math.random() * 17 + 11;
        qwhour = (int) Math.random() * 14 + 11;
        qwminute = (int) Math.random() * 49 + 11;
        qwsecond = (int) Math.random() * 49 + 11;
        qwcreateYear = qwyear - 1;
        qwcreateMonth = qwmonth -1;
        qwcreateDay = qwday - 1;


//        status
        String[] qwstatusArr = {"투자진행", "투자마감", "상품제조중", "상품제조완료", "제품배송중", "제품배송완료", "성공", "실패"};
        String qwstatusInput = statusArr[random.nextInt(8) + 1 - 1].toString();

        int qwclickCountRandom = random.nextInt(50);





        String createRandomDataSql ="";
//

        for (int i = 1; i < 100; i++) {
//            seqid = seqid + i + ", ";
            seqid = i + ", ";
            productName = "\"" + productName + i + "\", ";
            information = "\"" + information + i + "\", ";
            totalPrice = Integer.toString(qwrandomTotalPrice) + ", ";
            investPrice = Integer.toString(qwrandomInvestPrice) + ", ";
            period = Integer.toString(qwyear) + Integer.toString(qwmonth) +Integer.toString(qwday) + Integer.toString(qwhour) + Integer.toString(qwminute) + Integer.toString(qwsecond) + ", ";
            productStatus = "\'" + qwstatusInput + "\'"  + ", ";
            createDate = Integer.toString(qwcreateYear) + Integer.toString(qwcreateMonth) + Integer.toString(qwcreateDay) + Integer.toString(qwhour) + Integer.toString(qwminute) + Integer.toString(qwsecond) + ", ";
            clickCount = Integer.toString(qwclickCountRandom);


            createRandomDataSql += middleSql + seqid + productName + information + totalPrice + investPrice + period + productStatus + createDate + clickCount + endSql + ", ";
            seqid = "";
            productName = "testProduct";
            information = "testInformation";
            totalPrice = "";
            investPrice = "";
            period = "";
            productStatus = "";
            createDate = "";
            clickCount = "";
        }
        for (int i = 100; i < 101; i++) {
            seqid = i + ", ";
            productName = "\"" + productName + i + "\", ";
            information = "\"" + information + i + "\", ";
            totalPrice = Integer.toString(qwrandomTotalPrice) + ", ";
            investPrice = Integer.toString(qwrandomInvestPrice) + ", ";
            period = Integer.toString(qwyear) + Integer.toString(qwmonth) +Integer.toString(qwday) + Integer.toString(qwhour) + Integer.toString(qwminute) + Integer.toString(qwsecond) + ", ";
            productStatus = "\'" + qwstatusInput + "\'"  + ", ";
            createDate = Integer.toString(qwcreateYear) + Integer.toString(qwcreateMonth) + Integer.toString(qwcreateDay) + Integer.toString(qwhour) + Integer.toString(qwminute) + Integer.toString(qwsecond) + ", ";
            clickCount = Integer.toString(qwclickCountRandom);


            createRandomDataSql += middleSql + seqid + productName + information + totalPrice + investPrice + period + productStatus + createDate + clickCount + endSql + ";";
        }

        String sc = sql + createRandomDataSql;
        Query nativeQuery = em.createNativeQuery(sc);
        System.out.println(nativeQuery);
        System.out.println(sc);
        return sc;
    }


}
//    하나만 가져오는 것
//    매개 변수에 넣을 것이 pk
//    (이거를 검색 로직에 적용도 가능 할 것 같은데 어떤 매개변수가 들어오는지는
//    컨트롤러에서 해야줘야하나?
//    일단 pk 는 id 로 설정해서 하나만 가져와 보자
//    작동 잘 됨 ㅇㅇ

//    페이징을 위한 function
//    5개를 가져올 것임
//    offset = 페이징의 시작 점
//    limit = 몇개를 가져올 것인지
//    일단은 5개로 고정 해놓자
//    jpa는 limit 구문이 안먹힘
//    setFirstResult();
//    setMaxResults();
//    이거로 설정을 해줘야함
//    product table 의 타입이 int가 아니어서 역정렬 했을때 이상하게 나왔음
//    alter table product modify id int(10);
//    이렇게 테이블의 데이터 타입을 변경 해주니까 잘 나옴
//    public List<Product> findFiveProduct(int pagingId) {
//        int limit = 5;
//
//        return em.createQuery(
//                "select i from Product i where investment is not Null order by id desc", Product.class)
//                .setFirstResult(pagingId)
//                .setMaxResults(limit)
//                .getResultList();
//    }

//    페이징 용 컬럼에 있는 데이터 갯수만 가져오는 것
//    null 값 구별해서 갯수에 포함 안하는 걸로 수정
//    최대 10개만 보여주도록 설정해야함

//    public Object getRow() {
//        Object row = em.createQuery("select count(*) from Product i where i.investment is not null")
//                .getSingleResult();
//
//        return row;
//    }
