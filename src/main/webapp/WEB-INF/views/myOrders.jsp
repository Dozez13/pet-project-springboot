<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="/WEB-INF/ResourceBundleUTF.tld"%>
<%@taglib prefix="log" uri="http://logging.apache.org/log4j/tld/log"%>


<log:setLogger logger="myOrders" var="myOrders"/>
<log:log level="info" logger="${myOrders}" message="Index jsp has been visited"/>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang :pageContext.request.locale.language}" scope="session" />
<fmt:setLocale name="${lang}" />
<fmt:setBundle basename="resources" />
<!DOCTYPE html>
<html lang="${lang}">
<head>
    <title>MyOrders</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet" type="text/css">
    <link href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" rel="stylesheet" type="text/css">
    <link href="https://maxst.icons8.com/vue-static/landings/line-awesome/font-awesome-line-awesome/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/animate.css@^4.0.0/animate.min.css" rel="stylesheet" type="text/css">
    <link href="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.min.css" rel="stylesheet" type="text/css">
    <script>
        window.quasarConfig = {
            brand: {
                primary: '#003cd4',
                secondary: '#26a679',
                accent: '#9C27B0',
                dark: '#1d1d1d',
                positive: '#e3ffc9',
                negative: '#C10015',
                info: '#32eda5',
                warning: '#ffbb00'
            }
        }
    </script>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/vue@^2.0.0/dist/vue.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/quasar@1.15.13/dist/quasar.umd.modern.min.js"></script>
<div id="q-app">
    <div>
        <%@include file="../fichiers/headerHome.jsp"%>
    </div>
    <div class="q-pa-md row justify-center q-gutter-md">
        <c:if test="${totalOrders!=0}">
        <q-card>
            <c:forEach items="${myOrders}" var="myOrder" >

            <q-card-section horizontal class="bg-purple text-white">
                <q-card-section class="bg-primary text-white">
                    <div class="text-h6"><fmt:localeValue key="orderAddress"/></div>
                    <q-separator color="black" spaced size="5px"></q-separator>
                    <div class="text-h6">${myOrder.userAddress}</div>
                </q-card-section>
                <q-card-section class="bg-green-9 text-white">
                    <div class="text-h6"><fmt:localeValue key="orderDestinationAddressTitle"/></div>
                    <q-separator color="black" spaced size="5px"></q-separator>
                    <div class="text-h6">${myOrder.userDestination}</div>
                </q-card-section>
                <q-card-section class="bg-teal-8 text-white">
                    <div class="text-h6"><fmt:localeValue key="ordersLabelOrderCost"/></div>
                    <q-separator color="black" spaced size="5px"></q-separator>
                    <div class="text-h6">${myOrder.orderCost}</div>
                </q-card-section>
                <q-card-section class="bg-blue-10 text-white">
                    <div class="text-h6"><fmt:localeValue key="ordersLabelOrderDate"/></div>
                    <q-separator color="black" spaced size="5px"></q-separator>
                    <div class="text-h6">${myOrder.orderDate}</div>
                </q-card-section>
            </q-card-section>
            <q-separator color="white" size="5px"></q-separator>
            </c:forEach>
            <q-card-section align="right">

                <q-btn :disabled="lessPage" type="a" href="${pageContext.request.contextPath}/user/myOrders?lang=${sessionScope.lang}&startRow=${requestScope.startRow-3}&currentPage=${requestScope.currentPage-1}" label="
&#8656;" ></q-btn>
                <span>${currentPage}</span>
                <q-btn :disabled="morePage" type="a" href="${pageContext.request.contextPath}/user/myOrders?lang=${sessionScope.lang}&startRow=${requestScope.startRow+3}&currentPage=${requestScope.currentPage+1}" label="
&#8658;"></q-btn>
            </q-card-section>
        </q-card>
        </c:if>
        <c:if test="${totalOrders==0}">
            <q-card>
                <q-card-section class="bg-blue-10 text-white">
                    <fmt:localeValue key="ordersNotFoundMessage"/>
                </q-card-section>
            </q-card>
        </c:if>
    </div>

</div>


</body>
<script>
    new Vue({
        el: '#q-app',
        data () {
            return {
                lessPage:${currentPage==1},
                morePage:${currentPage==totalOrders},
                Eng:'',
                Ua:'',
                Ru:'',


            }
        },
        methods:{
            goHome(){
                window.location.href ='${pageContext.request.contextPath}'+'/index?lang=${sessionScope.lang}';
            },
            makeOrder(){
                window.location.href='${pageContext.request.contextPath}'+'/user/order?lang=${sessionScope.lang}';
            },
            myOrders(){
                window.location.href='${pageContext.request.contextPath}'+'/user/myOrders?lang=${sessionScope.lang}';
            },
            checkOrders(){
                window.location.href='${pageContext.request.contextPath}'+'/admin/orders?lang=${sessionScope.lang}';
            },
            profilePage(){
                window.location.href ='${pageContext.request.contextPath}'+'/user/profile?lang=${sessionScope.lang}';
            },
            changeToEng(){
                window.location.href = window.location.href+'?lang=en'
            },
            changeToUa(){
                window.location.href = window.location.href+'?lang=ua'
            },
            changeToRu(){
                window.location.href = window.location.href+'?lang=ru'
            }
        },
        mounted: function(){
            window.history.pushState({}, document.title,window.location.href.split(/[?#]/)[0]);
            switch ('${sessionScope.lang}'){
                case 'en':{
                    this.Eng='black'
                    break
                }
                case 'ua':
                case 'uk_UA':{
                    this.Ua='black'
                    break
                }
                case 'ru':{
                    this.Ru='black'
                    break
                }
            }
        }
    })
</script>
</html>
