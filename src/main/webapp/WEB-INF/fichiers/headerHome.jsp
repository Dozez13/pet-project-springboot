<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<q-toolbar  class="bg-primary text-white shadow-2 q-pa-md">
    <q-separator dark vertical inset></q-separator>
    <span class="q-ml-md text-h5"><fmt:localeValue key="headerCompanyName"/></span>
    <q-separator dark vertical inset></q-separator>
    <q-btn size="md" @click="goHome()" stretch flat label="<fmt:localeValue key="headerHomePage"/>"></q-btn>
    <q-space></q-space>
    <q-btn-dropdown size="md" icon="perm_identity" stretch flat label="<fmt:localeValue key="headerAccountSettings"/>" >
        <div class="row no-wrap q-pa-md justify-around">

            <security:authorize access="!isAuthenticated()">
            <q-btn label="<fmt:localeValue key="singIn"/>" color="primary" size="md" @click="() => { showDialogD = true }"></q-btn>
            <div>
                <q-dialog v-model="showDialogD"  no-backdrop-dismiss>
                    <div>
                        <q-form
                                action="${pageContext.request.contextPath}/index" method="post"
                        >
                            <q-card style="width: 400px;">
                                <q-toolbar class="bg-primary text-white">
                                    <q-toolbar-title>
                                        <fmt:localeValue key="singIn"/>
                                    </q-toolbar-title>
                                    <q-btn flat round color="white" icon="close" v-close-popup></q-btn>
                                </q-toolbar>
                                <q-card-section class="inset-shadow"/>
                                <security:csrfInput />
                                <q-input
                                        filled
                                        debounce="300"
                                        type="text"
                                        :hint="LogHintD"
                                        v-model="loginD"
                                        label="<fmt:localeValue key="dialogLogin"/>"
                                        name="login"
                                        class="q-ma-xs"
                                        :rules="[ val => val.match(/(^([a-z-а-яА-ЯЇЄЁЭїєёэ]{5,19})$)/ig)!=null || '<fmt:localeValue key="loginValidation" />']"
                                >
                                    <template v-slot:before>
                                        <q-icon name="login"></q-icon>
                                    </template>
                                </q-input>
                                <q-input v-model="passwordD"
                                         class="q-ma-xs"
                                         debounce="300"
                                         name="psw"
                                         label="<fmt:localeValue key="dialogPassword"/>"
                                         :rules="[ val => val.match(/(^([\wа-яА-ЯЇЄЁЭїєёэ]{5,20})$)/ig)!=null || '<fmt:localeValue key="passwordValidation"/>']"
                                         filled :type="isPwdD ? 'password' : 'text'"
                                         :hint="PasswordHintD">
                                    <template v-slot:before>
                                        <q-icon
                                                :name="isPwdD ? 'visibility_off' : 'visibility'"
                                                class="cursor-pointer"
                                                @click="isPwdD = !isPwdD"
                                        ></q-icon>
                                    </template>
                                </q-input>
                                <q-btn class="q-ma-md" label="<fmt:localeValue key="dialogLogInToAccount"/>" type="submit" color="primary"/>

                            </q-card>
                        </q-form>
                    </div>
                </q-dialog>
            </div>
            <q-btn
                    color="primary"
                    label="<fmt:localeValue key="headerSignUp"/>"
                    push
                    @click="registrationPage()"
                    col="col-6"
                    size="md"
                    v-close-popup
            ></q-btn>
            </security:authorize>
<security:authorize access="isAuthenticated()">
                <div class="column justify-center">
                    <div class="text-h6"><fmt:localeValue key="indexSettings"/></div>
                    <q-btn  color="primary" @click="profilePage()" stretch flat label="<fmt:localeValue key="loggedProfile"/>"></q-btn>
    <security:authorize access="hasRole('ROLE_CLIENT')&&!hasRole('ROLE_ADMIN')">
                        <q-btn color="primary" @click="myOrders()" stretch flat label="<fmt:localeValue key="myOrders"/>">
                        </q-btn>
    </security:authorize>
                </div>

                <q-separator vertical inset class="q-mx-lg"></q-separator>
                <div class="column items-center">
                        <q-avatar size="72px" icon="portrait"></q-avatar>
                    <div class="text-subtitle1 q-mt-md q-mb-xs">
                        <security:authentication property="principal.username"/>
                    </div>
                    <div class="text-subtitle1 q-mt-md q-mb-xs">
                        <security:authentication property="principal.authorities" var="authorities" />
                        <c:forEach items="${authorities}" var="authority" varStatus="vs">
                            <p>${authority.authority}</p>
                        </c:forEach>
                    </div>
                    <q-form
                            action="${pageContext.request.contextPath}/logout" method="post"
                    >
                        <security:csrfInput />
                    <q-btn
                            color="primary"
                            label="<fmt:localeValue key="loggedLogout"/>"
                            push
                            size="md"
                            v-close-popup
                            type="submit"
                    ></q-btn>
                    </q-form>
                </div>

</security:authorize>
        </div>
    </q-btn-dropdown>
<security:authorize access="isAuthenticated()&& hasRole('ROLE_CLIENT')&&!hasRole('ROLE_ADMIN')">

        <q-btn size="md" @click="makeOrder()" stretch flat label="<fmt:localeValue key="headerMakeOrder"/>"></q-btn>
</security:authorize>
    <q-separator dark vertical></q-separator>
    <q-btn size="md" :color="Eng" @click="changeToEng()"  label="EN"></q-btn>
    <q-btn size="md" :color="Ua" @click="changeToUa()"  label="UA"></q-btn>
    <q-btn size="md" :color="Ru" @click="changeToRu()"  label="RU"></q-btn>
   <security:authorize access="hasRole('ROLE_ADMIN')">
        <q-btn size="md" @click="checkOrders()" stretch flat label="<fmt:localeValue key="ClientsOrders"/>"></q-btn>
   </security:authorize>

</q-toolbar>
