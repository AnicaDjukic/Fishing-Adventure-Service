(function(e){function t(t){for(var a,o,i=t[0],l=t[1],s=t[2],u=0,d=[];u<i.length;u++)o=i[u],Object.prototype.hasOwnProperty.call(c,o)&&c[o]&&d.push(c[o][0]),c[o]=0;for(a in l)Object.prototype.hasOwnProperty.call(l,a)&&(e[a]=l[a]);b&&b(t);while(d.length)d.shift()();return r.push.apply(r,s||[]),n()}function n(){for(var e,t=0;t<r.length;t++){for(var n=r[t],a=!0,o=1;o<n.length;o++){var i=n[o];0!==c[i]&&(a=!1)}a&&(r.splice(t--,1),e=l(l.s=n[0]))}return e}var a={},o={app:0},c={app:0},r=[];function i(e){return l.p+"js/"+({}[e]||e)+"."+{"chunk-09ee1a4a":"8da37420","chunk-1162162b":"7cf68f49","chunk-23cb1359":"096026c2","chunk-2832d883":"87dd4136","chunk-2e53f58d":"0ddd2c68","chunk-45d11442":"774db97d","chunk-6fa4def7":"9eec1aa8","chunk-73dafbd9":"5cd1faaa","chunk-76012225":"d3740b53","chunk-7e11ccc5":"38aeb960","chunk-a64cef48":"37db59a9","chunk-10c7e4bb":"15639ad6","chunk-12f7d5ac":"080d4e0f","chunk-2964e9ae":"290a0077","chunk-331d75e0":"dc75a30c","chunk-41d9085f":"c7fda418","chunk-69b08594":"e37db3b2","chunk-0dc54e32":"be191aa1","chunk-6ad50bff":"495c4458","chunk-f7c4ae44":"511f8d6b","chunk-fe77485e":"63061fe3","chunk-c6739a70":"364cf103","chunk-3856987e":"4b09599c","chunk-60a1ccae":"7a902c3d","chunk-e1e74576":"e9811c49","chunk-19bbd2e3":"62ab843e","chunk-6f4f5344":"3b855500"}[e]+".js"}function l(t){if(a[t])return a[t].exports;var n=a[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,l),n.l=!0,n.exports}l.e=function(e){var t=[],n={"chunk-09ee1a4a":1,"chunk-1162162b":1,"chunk-23cb1359":1,"chunk-2832d883":1,"chunk-2e53f58d":1,"chunk-45d11442":1,"chunk-6fa4def7":1,"chunk-73dafbd9":1,"chunk-76012225":1,"chunk-7e11ccc5":1,"chunk-10c7e4bb":1,"chunk-12f7d5ac":1,"chunk-2964e9ae":1,"chunk-331d75e0":1,"chunk-41d9085f":1,"chunk-69b08594":1,"chunk-0dc54e32":1,"chunk-6ad50bff":1,"chunk-f7c4ae44":1,"chunk-fe77485e":1,"chunk-c6739a70":1,"chunk-3856987e":1,"chunk-60a1ccae":1,"chunk-e1e74576":1,"chunk-19bbd2e3":1,"chunk-6f4f5344":1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var a="css/"+({}[e]||e)+"."+{"chunk-09ee1a4a":"8fd3402d","chunk-1162162b":"01d0693d","chunk-23cb1359":"20a34a28","chunk-2832d883":"061220b1","chunk-2e53f58d":"365659eb","chunk-45d11442":"941d232a","chunk-6fa4def7":"66f8507b","chunk-73dafbd9":"0f47faa0","chunk-76012225":"ccfba659","chunk-7e11ccc5":"d024ed87","chunk-a64cef48":"31d6cfe0","chunk-10c7e4bb":"a178b40a","chunk-12f7d5ac":"d34ef09b","chunk-2964e9ae":"876f341d","chunk-331d75e0":"3bed2816","chunk-41d9085f":"495e2e1b","chunk-69b08594":"9659b921","chunk-0dc54e32":"73eba3c9","chunk-6ad50bff":"8c63b7e9","chunk-f7c4ae44":"0c556c37","chunk-fe77485e":"738552be","chunk-c6739a70":"cb6fd908","chunk-3856987e":"e1cbedf1","chunk-60a1ccae":"4c2d70c6","chunk-e1e74576":"16073698","chunk-19bbd2e3":"a7c8e671","chunk-6f4f5344":"f8d9dea9"}[e]+".css",c=l.p+a,r=document.getElementsByTagName("link"),i=0;i<r.length;i++){var s=r[i],u=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(u===a||u===c))return t()}var d=document.getElementsByTagName("style");for(i=0;i<d.length;i++){s=d[i],u=s.getAttribute("data-href");if(u===a||u===c)return t()}var b=document.createElement("link");b.rel="stylesheet",b.type="text/css",b.onload=t,b.onerror=function(t){var a=t&&t.target&&t.target.src||c,r=new Error("Loading CSS chunk "+e+" failed.\n("+a+")");r.code="CSS_CHUNK_LOAD_FAILED",r.request=a,delete o[e],b.parentNode.removeChild(b),n(r)},b.href=c;var h=document.getElementsByTagName("head")[0];h.appendChild(b)})).then((function(){o[e]=0})));var a=c[e];if(0!==a)if(a)t.push(a[2]);else{var r=new Promise((function(t,n){a=c[e]=[t,n]}));t.push(a[2]=r);var s,u=document.createElement("script");u.charset="utf-8",u.timeout=120,l.nc&&u.setAttribute("nonce",l.nc),u.src=i(e);var d=new Error;s=function(t){u.onerror=u.onload=null,clearTimeout(b);var n=c[e];if(0!==n){if(n){var a=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+a+": "+o+")",d.name="ChunkLoadError",d.type=a,d.request=o,n[1](d)}c[e]=void 0}};var b=setTimeout((function(){s({type:"timeout",target:u})}),12e4);u.onerror=u.onload=s,document.head.appendChild(u)}return Promise.all(t)},l.m=e,l.c=a,l.d=function(e,t,n){l.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},l.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},l.t=function(e,t){if(1&t&&(e=l(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(l.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)l.d(n,a,function(t){return e[t]}.bind(null,a));return n},l.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return l.d(t,"a",t),t},l.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},l.p="/",l.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],u=s.push.bind(s);s.push=t,s=s.slice();for(var d=0;d<s.length;d++)t(s[d]);var b=u;r.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"25fd":function(e,t,n){},"3cdd":function(e,t,n){},"428c":function(e,t,n){e.exports=n.p+"img/logo_white.9f195821.png"},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var a=n("7a23");function o(e,t,n,o,c,r){var i=Object(a["O"])("NavBar"),l=Object(a["O"])("router-view"),s=Object(a["O"])("Footer");return Object(a["F"])(),Object(a["h"])(a["a"],null,[Object(a["m"])(i,{id:"bar"}),Object(a["m"])(l),Object(a["m"])(s,{id:"footer"})],64)}n("caad"),n("2532");var c=n("428c"),r=n.n(c),i=function(e){return Object(a["I"])("data-v-96940674"),e=e(),Object(a["G"])(),e},l={class:"navbar sticky-top navbar-expand-lg navbar-dark bg-dark"},s={class:"container-fluid"},u=i((function(){return Object(a["i"])("a",{class:"navbar-brand",href:"/"},[Object(a["i"])("img",{src:r.a,alt:"",width:"150",height:"50"})],-1)})),d=i((function(){return Object(a["i"])("button",{class:"navbar-toggler",type:"button","data-bs-toggle":"collapse","data-bs-target":"#navbarScroll","aria-controls":"navbarScroll","aria-expanded":"false","aria-label":"Toggle navigation"},[Object(a["i"])("span",{class:"navbar-toggler-icon"})],-1)})),b={class:"collapse navbar-collapse",id:"navbarScroll"},h={class:"navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll"},f=i((function(){return Object(a["i"])("li",{class:"nav-item"},[Object(a["i"])("a",{class:"nav-link","aria-current":"page",href:"/"},"Home")],-1)})),p={class:"nav-item dropdown"},O={key:0,class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false"},m={key:1,class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false"},g=i((function(){return Object(a["i"])("ul",{class:"dropdown-menu text-center dropdown-menu-dark","aria-labelledby":"navbarScrollingDropdown"},[Object(a["i"])("li",null,[Object(a["i"])("a",{class:"dropdown-item",href:"/search/boats"},"Boats")]),Object(a["i"])("li",null,[Object(a["i"])("a",{class:"dropdown-item",href:"/search/cottages"},"Cottages")]),Object(a["i"])("li",null,[Object(a["i"])("a",{class:"dropdown-item",href:"/search/adventures"},"Adventures")])],-1)})),k={class:"nav-item dropdown"},j={key:0,class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false"},v={class:"dropdown-menu text-center dropdown-menu-dark","aria-labelledby":"navbarScrollingDropdown"},R={key:0,class:"dropdown-item",href:"/client/pastReservations"},y={key:0,class:"dropdown-item",href:"/client/currentReservations"},E={key:0,class:"dropdown-item",href:"/clientReviews"},I={key:0,class:"dropdown-item",href:"/clientComplaints"},w={key:0,class:"dropdown-item",href:"/advertiserComplaints"},_={key:0,class:"nav-item"},N=i((function(){return Object(a["i"])("a",{class:"nav-link","aria-current":"page",href:"/client/subscriptions"},"Subscriptions",-1)})),C=[N],L={key:1,class:"nav-item dropdown"},A={key:2,class:"nav-item dropdown"},T=i((function(){return Object(a["i"])("a",{class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false"}," My advertisments ",-1)})),S={class:"dropdown-menu text-center dropdown-menu-dark","aria-labelledby":"navbarScrollingDropdown"},F={key:0},x=i((function(){return Object(a["i"])("a",{class:"dropdown-item",href:"/cottages"},"Cottages",-1)})),B=[x],M={key:1},P=i((function(){return Object(a["i"])("a",{class:"dropdown-item",href:"/fishingAdventures"},"Fishing adventures",-1)})),W=[P],U={key:2},H=i((function(){return Object(a["i"])("a",{class:"dropdown-item",href:"/boats"},"Boats",-1)})),D=[H],V={key:3},G=i((function(){return Object(a["i"])("a",{class:"dropdown-item",href:"/calendar"},"Calander",-1)})),q=[G],z={key:4},J=i((function(){return Object(a["i"])("a",{class:"dropdown-item",href:"/specialOffers"},"Special offers",-1)})),K=[J],Y={key:5},X=i((function(){return Object(a["i"])("a",{class:"dropdown-item",href:"/reservations"},"Reservations",-1)})),Q=[X],Z={key:3,class:"nav-item dropdown"},$=i((function(){return Object(a["i"])("a",{class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false"}," Users ",-1)})),ee=i((function(){return Object(a["i"])("ul",{class:"dropdown-menu text-center dropdown-menu-dark","aria-labelledby":"navbarScrollingDropdown"},[Object(a["i"])("li",null,[Object(a["i"])("a",{class:"dropdown-item",href:"/users"},"All users")]),Object(a["i"])("li",null,[Object(a["i"])("a",{class:"dropdown-item",href:"/registrationRequests"},"Registration Requests")]),Object(a["i"])("li",null,[Object(a["i"])("a",{class:"dropdown-item",href:"/deleteRequests"},"Deletion requests")])],-1)})),te=[$,ee],ne={key:0,class:"d-flex"},ae=i((function(){return Object(a["i"])("button",{type:"button",class:"btn btn-outline-primary","data-bs-toggle":"modal","data-bs-target":"#LogInModal"}," Register / Log In ",-1)})),oe=[ae],ce={key:1,class:"d-flex"},re=i((function(){return Object(a["i"])("i",{class:"fas fa-user"},null,-1)})),ie=[re];function le(e,t,n,o,c,r){var i=Object(a["O"])("register-modal");return Object(a["F"])(),Object(a["h"])("div",null,[Object(a["i"])("nav",l,[Object(a["i"])("div",s,[u,d,Object(a["i"])("div",b,[Object(a["i"])("ul",h,[f,Object(a["i"])("li",p,["ROLE_ADMIN"!=e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",O," Explore ")):(Object(a["F"])(),Object(a["h"])("a",m," Services ")),g]),Object(a["i"])("li",k,["ROLE_CLIENT"==e.loggedInRole||"ROLE_ADMIN"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",j," Reservations ")):Object(a["g"])("",!0),Object(a["i"])("ul",v,[Object(a["i"])("li",null,["ROLE_CLIENT"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",R,"Past reservations")):Object(a["g"])("",!0)]),Object(a["i"])("li",null,["ROLE_CLIENT"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",y,"Current reservations")):Object(a["g"])("",!0)]),Object(a["i"])("li",null,["ROLE_ADMIN"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",E,"Reviews")):Object(a["g"])("",!0)]),Object(a["i"])("li",null,["ROLE_ADMIN"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",I,"Complaints")):Object(a["g"])("",!0)]),Object(a["i"])("li",null,["ROLE_ADMIN"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("a",w,"Reports")):Object(a["g"])("",!0)])])]),"ROLE_CLIENT"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",_,C)):Object(a["g"])("",!0),"homeOwner"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",L)):Object(a["g"])("",!0),"ROLE_VACATION_HOME_OWNER"==e.loggedInRole||"ROLE_FISHING_INSTRUCTOR"==e.loggedInRole||"ROLE_BOAT_OWNER"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",A,[T,Object(a["i"])("ul",S,["ROLE_VACATION_HOME_OWNER"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",F,B)):Object(a["g"])("",!0),"ROLE_FISHING_INSTRUCTOR"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",M,W)):Object(a["g"])("",!0),"ROLE_BOAT_OWNER"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",U,D)):Object(a["g"])("",!0),"ROLE_VACATION_HOME_OWNER"==e.loggedInRole||"ROLE_FISHING_INSTRUCTOR"==e.loggedInRole||"ROLE_BOAT_OWNER"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",V,q)):Object(a["g"])("",!0),"ROLE_VACATION_HOME_OWNER"==e.loggedInRole||"ROLE_FISHING_INSTRUCTOR"==e.loggedInRole||"ROLE_BOAT_OWNER"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",z,K)):Object(a["g"])("",!0),"ROLE_FISHING_INSTRUCTOR"==e.loggedInRole||"ROLE_VACATION_HOME_OWNER"==e.loggedInRole||"ROLE_BOAT_OWNER"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",Y,Q)):Object(a["g"])("",!0)])])):Object(a["g"])("",!0),"ROLE_ADMIN"==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("li",Z,te)):Object(a["g"])("",!0)]),void 0==e.loggedInRole?(Object(a["F"])(),Object(a["h"])("div",ne,oe)):Object(a["g"])("",!0),void 0!=e.loggedInRole?(Object(a["F"])(),Object(a["h"])("div",ce,[Object(a["i"])("button",{type:"button",class:"btn btn-outline-primary me-2",onClick:t[0]||(t[0]=function(){return r.logOut&&r.logOut.apply(r,arguments)})}," Log out "),Object(a["i"])("button",{type:"button",class:"btn btn-outline-primary",onClick:t[1]||(t[1]=function(){return r.openProfile&&r.openProfile.apply(r,arguments)})},ie)])):Object(a["g"])("",!0)])])]),Object(a["m"])(i)])}n("b0c0");var se=function(e){return Object(a["I"])("data-v-15fe5993"),e=e(),Object(a["G"])(),e},ue={class:"modal fade dark",id:"LogInModal",tabindex:"-1","aria-labelledby":"LogInModalLabel","aria-hidden":"true"},de={class:"modal-dialog modal-dialog-centered"},be={class:"modal-content"},he={class:"modal-header"},fe={class:"nav nav-tabs"},pe={class:"nav-item"},Oe={class:"nav-item"},me=se((function(){return Object(a["i"])("i",{class:"fas fa-times fa-lg"},null,-1)})),ge=[me],ke={key:0,class:"modal-body"},je=se((function(){return Object(a["i"])("h5",null,"Please enter your information",-1)})),ve={class:"row"},Re={class:"row"},ye=se((function(){return Object(a["i"])("label",{class:"error",id:"emptyFieldsError",name:"labels",display:"hidden"},null,-1)})),Ee={key:1,class:"modal-body"},Ie=se((function(){return Object(a["i"])("label",{class:"error",id:"emptyFieldsError",name:"labels",display:"hidden"},null,-1)})),we={key:2,class:"modal-body"},_e=se((function(){return Object(a["i"])("h6",null,"Choose your role on our site:",-1)})),Ne={class:"btn-group",role:"role"},Ce=["checked"],Le=se((function(){return Object(a["i"])("label",{class:"btn btn-outline-primary",for:"customer-radio-btn"},[Object(a["i"])("i",{class:"fas fa-user fa-lg"}),Object(a["i"])("br"),Object(a["l"])(" Client")],-1)})),Ae=["checked"],Te=se((function(){return Object(a["i"])("label",{class:"btn btn-outline-primary",for:"boat-radio-btn"},[Object(a["i"])("i",{class:"fas fa-anchor fa-lg"}),Object(a["i"])("br"),Object(a["l"])(" Boat owner")],-1)})),Se=["checked"],Fe=se((function(){return Object(a["i"])("label",{class:"btn btn-outline-primary",for:"home-radio-btn"},[Object(a["i"])("i",{class:"fas fa-home fa-lg"}),Object(a["i"])("br"),Object(a["l"])(" Home owner")],-1)})),xe=["checked"],Be=se((function(){return Object(a["i"])("label",{class:"btn btn-outline-primary",for:"instructor-radio-btn"},[Object(a["i"])("i",{class:"fas fa-fish fa-lg"}),Object(a["i"])("br"),Object(a["l"])(" Fishing instructor")],-1)})),Me={key:3,class:"modal-body"},Pe=se((function(){return Object(a["i"])("h5",null,"Please inform us about reasons for joining our site",-1)})),We={class:"form-floating"},Ue=se((function(){return Object(a["i"])("label",{for:"floatingTextarea"},"Reasons for joining",-1)})),He={class:"modal-footer"},De=se((function(){return Object(a["i"])("p",{id:"msg"},null,-1)})),Ve=se((function(){return Object(a["i"])("i",{class:"fas fa-chevron-left fa-xs"},null,-1)})),Ge=Object(a["l"])(" Back "),qe=[Ve,Ge],ze=Object(a["l"])(" Next "),Je=se((function(){return Object(a["i"])("i",{class:"fas fa-chevron-right fa-xs"},null,-1)})),Ke=[ze,Je];function Ye(e,t,n,o,c,r){return Object(a["F"])(),Object(a["h"])("div",ue,[Object(a["i"])("div",de,[Object(a["i"])("div",be,[Object(a["i"])("div",he,[Object(a["i"])("ul",fe,[Object(a["i"])("li",pe,[Object(a["i"])("a",{class:"nav-link","aria-current":"page",href:"#",id:"logIn-btn",onClick:t[0]||(t[0]=function(){return r.logInClick&&r.logInClick.apply(r,arguments)})},"Log in")]),Object(a["i"])("li",Oe,[Object(a["i"])("a",{class:"nav-link",id:"register-btn",href:"#",onClick:t[1]||(t[1]=function(){return r.registerClick&&r.registerClick.apply(r,arguments)})},"Register")])]),Object(a["i"])("button",{type:"button",class:"btn-close","data-bs-dismiss":"modal",onClick:t[2]||(t[2]=function(){return r.closeModal&&r.closeModal.apply(r,arguments)})},ge)]),"registerCustomer"===e.mode||"registerAdvertiser"===e.mode?(Object(a["F"])(),Object(a["h"])("div",ke,[je,Object(a["i"])("form",null,[Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"login-inputs",placeholder:"E-mail","onUpdate:modelValue":t[3]||(t[3]=function(t){return e.user.email=t})},null,512),[[a["W"],e.user.email]]),Object(a["i"])("div",ve,[Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"col login-inputs",placeholder:"Name","onUpdate:modelValue":t[4]||(t[4]=function(t){return e.user.name=t})},null,512),[[a["W"],e.user.name]]),Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"col login-inputs",placeholder:"Surname","onUpdate:modelValue":t[5]||(t[5]=function(t){return e.user.surname=t})},null,512),[[a["W"],e.user.surname]])]),Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"login-inputs",placeholder:"Address","onUpdate:modelValue":t[6]||(t[6]=function(t){return e.user.street=t})},null,512),[[a["W"],e.user.street]]),Object(a["i"])("div",Re,[Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"col login-inputs",placeholder:"City","onUpdate:modelValue":t[7]||(t[7]=function(t){return e.user.city=t})},null,512),[[a["W"],e.user.city]]),Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"col login-inputs",placeholder:"Country","onUpdate:modelValue":t[8]||(t[8]=function(t){return e.user.country=t})},null,512),[[a["W"],e.user.country]])]),Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"login-inputs",placeholder:"Phone number","onUpdate:modelValue":t[9]||(t[9]=function(t){return e.user.phoneNumber=t})},null,512),[[a["W"],e.user.phoneNumber]]),Object(a["ab"])(Object(a["i"])("input",{type:"password",class:"login-inputs",placeholder:"Enter password","onUpdate:modelValue":t[10]||(t[10]=function(t){return e.user.password1=t})},null,512),[[a["W"],e.user.password1]]),Object(a["ab"])(Object(a["i"])("input",{type:"password",class:"login-inputs",placeholder:"Enter password again","onUpdate:modelValue":t[11]||(t[11]=function(t){return e.user.password2=t})},null,512),[[a["W"],e.user.password2]]),ye])])):Object(a["g"])("",!0),"logIn"===e.mode?(Object(a["F"])(),Object(a["h"])("div",Ee,[Object(a["i"])("form",null,[Object(a["ab"])(Object(a["i"])("input",{type:"text",class:"login-inputs",placeholder:"E-mail","onUpdate:modelValue":t[12]||(t[12]=function(t){return e.user.email=t})},null,512),[[a["W"],e.user.email]]),Object(a["ab"])(Object(a["i"])("input",{type:"password",class:"login-inputs",placeholder:"Password","onUpdate:modelValue":t[13]||(t[13]=function(t){return e.user.password1=t})},null,512),[[a["W"],e.user.password1]]),Ie])])):Object(a["g"])("",!0),"registerRole"===e.mode?(Object(a["F"])(),Object(a["h"])("div",we,[_e,Object(a["i"])("div",Ne,[Object(a["i"])("input",{type:"radio",class:"btn-check",name:"btnradio",id:"customer-radio-btn",autocomplete:"off",value:"ROLE_CLIENT",checked:"ROLE_CLIENT"==e.chosenRole},null,8,Ce),Le,Object(a["i"])("input",{type:"radio",class:"btn-check",name:"btnradio",id:"boat-radio-btn",autocomplete:"off",value:"ROLE_BOAT_OWNER",checked:"ROLE_BOAT_OWNER"==e.chosenRole},null,8,Ae),Te,Object(a["i"])("input",{type:"radio",class:"btn-check",name:"btnradio",id:"home-radio-btn",autocomplete:"off",value:"ROLE_VACATION_HOME_OWNER",checked:"ROLE_VACATION_HOME_OWNER"==e.chosenRole},null,8,Se),Fe,Object(a["i"])("input",{type:"radio",class:"btn-check",name:"btnradio",id:"instructor-radio-btn",autocomplete:"off",value:"ROLE_FISHING_INSTRUCTOR",checked:"ROLE_FISHING_INSTRUCTOR"==e.chosenRole},null,8,xe),Be])])):Object(a["g"])("",!0),"advertiserInfo"===e.mode?(Object(a["F"])(),Object(a["h"])("div",Me,[Pe,Object(a["i"])("div",We,[Object(a["ab"])(Object(a["i"])("textarea",{class:"form-control",placeholder:"Leave a comment here",id:"floatingTextarea","onUpdate:modelValue":t[14]||(t[14]=function(t){return e.registerReasons=t})},null,512),[[a["W"],e.registerReasons]]),Ue])])):Object(a["g"])("",!0),Object(a["i"])("div",He,[De,"logIn"==e.mode?(Object(a["F"])(),Object(a["h"])("button",{key:0,type:"button",class:"btn btn-outline-primary",onClick:t[15]||(t[15]=function(){return r.loginUser&&r.loginUser.apply(r,arguments)})}," Log in ")):Object(a["g"])("",!0),"registerCustomer"==e.mode||"registerAdvertiser"==e.mode||"advertiserInfo"==e.mode?(Object(a["F"])(),Object(a["h"])("button",{key:1,type:"button",class:"btn btn-outline-primary",onClick:t[16]||(t[16]=function(){return r.backClick&&r.backClick.apply(r,arguments)}),id:"back-btn"},qe)):Object(a["g"])("",!0),"registerCustomer"==e.mode||"registerAdvertiser"==e.mode?(Object(a["F"])(),Object(a["h"])("button",{key:2,onClick:t[17]||(t[17]=function(){return r.registerUser&&r.registerUser.apply(r,arguments)}),type:"button",id:"create-btn",class:"btn btn-outline-primary"}," Create account ")):Object(a["g"])("",!0),"registerRole"==e.mode||"advertiserInfo"==e.mode?(Object(a["F"])(),Object(a["h"])("button",{key:3,type:"button",class:"btn btn-outline-primary",onClick:t[18]||(t[18]=function(){return r.nextClick&&r.nextClick.apply(r,arguments)})},Ke)):Object(a["g"])("",!0)])])])])}var Xe=n("b85c"),Qe=n("bc3a"),Ze=n.n(Qe),$e={name:"RegisterModal",data:function(){return{mode:"logIn",chosenRole:"",registerReasons:"",user:{email:"",name:"",surname:"",street:"",city:"",country:"",phoneNumber:"",password1:"",password2:""}}},mounted:function(){var e=document.getElementById("logIn-btn");e.classList.add("active")},methods:{registerClick:function(){this.mode="registerRole";var e=document.getElementById("register-btn");e.classList.add("active"),e=document.getElementById("logIn-btn"),e.classList.remove("active")},logInClick:function(){this.mode="logIn";var e=document.getElementById("logIn-btn");e.classList.add("active"),e=document.getElementById("register-btn"),e.classList.remove("active"),document.getElementById("msg").innerHTML=""},nextClick:function(){var e,t=document.getElementsByName("btnradio"),n=Object(Xe["a"])(t);try{for(n.s();!(e=n.n()).done;){var a=e.value;a.checked&&(this.chosenRole=a.value)}}catch(o){n.e(o)}finally{n.f()}""!=this.registerReasons&&"advertiserInfo"==this.mode&&(this.mode="registerAdvertiser"),"ROLE_CLIENT"==this.chosenRole?this.mode="registerCustomer":"registerRole"==this.mode&&""!=this.chosenRole&&(this.mode="advertiserInfo")},backClick:function(){"registerCustomer"==this.mode||"advertiserInfo"===this.mode?this.mode="registerRole":"registerAdvertiser"==this.mode&&(this.mode="advertiserInfo")},closeModal:function(){this.mode="logIn",this.chosenRole="",this.registerReasons="",this.user.email="",this.user.name="",this.user.surname="",this.user.street="",this.user.city="",this.user.country="",this.user.phoneNumber="",this.user.password1="",this.user.password2="";var e=document.getElementById("logIn-btn");e.classList.add("active"),e=document.getElementById("register-btn"),e.classList.remove("active"),document.getElementById("create-btn").style.visibility="visible",document.getElementById("back-btn").style.visibility="visible",document.getElementById("msg").innerHTML=""},loginUser:function(){var e={email:this.user.email,password:this.user.password1};Ze.a.post("http://localhost:8080/auth/login",e,{headers:{"Access-Control-Allow-Origin":"http://localhost:8080"}}).then((function(e){window.location.reload(),""!=e.data&&(localStorage.setItem("jwt",e.data.accessToken),localStorage.setItem("refreshToken",e.data.refreshToken))}))},registerUser:function(){var e,t=this,n={email:this.user.email,password:this.user.password1,name:this.user.name,surname:this.user.surname,phoneNumber:this.user.phoneNumber,userType:{name:this.chosenRole},address:{street:this.user.street,city:this.user.city,country:this.user.country,zipCode:0},biography:this.registerReasons};switch(this.chosenRole){case"ROLE_CLIENT":e="http://localhost:8080/auth/signup";break;case"ROLE_BOAT_OWNER":e="http://localhost:8080/auth/signup/boatOwner";break;case"ROLE_VACATION_HOME_OWNER":e="http://localhost:8080/auth/signup/homeOwner";break;case"ROLE_FISHING_INSTRUCTOR":e="http://localhost:8080/auth/signup/fishingInstructor";break}Ze.a.post(e,n,{headers:{"Access-Control-Allow-Origin":"http://localhost:8080"}}).then((function(e){null!=e&&("ROLE_CLIENT"==t.chosenRole?document.getElementById("msg").innerHTML="A verification mail has been sent to your email address.":document.getElementById("msg").innerHTML="Your registration request has been sent to an admin for approval.",document.getElementById("create-btn").style.visibility="collapse",document.getElementById("back-btn").style.visibility="collapse")}))}}},et=(n("8f95"),n("6b0d")),tt=n.n(et);const nt=tt()($e,[["render",Ye],["__scopeId","data-v-15fe5993"]]);var at=nt,ot={components:{"register-modal":at},name:"NavBar",data:function(){return{loggedInRole:void 0}},mounted:function(){var e=this;localStorage.jwt?Ze.a.get("http://localhost:8080/users/getRoleIfActivated",{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){""!=t.data?e.loggedInRole=t.data:window.location.href="/"})):this.loggedInRole=void 0},methods:{openProfile:function(){window.location.href="/profile"},logOut:function(){localStorage.clear(),window.location.href="/"}}};n("f6a8");const ct=tt()(ot,[["render",le],["__scopeId","data-v-96940674"]]);var rt=ct,it=Object(a["k"])('<div class="footer" data-v-1f2e5a52><h4 data-v-1f2e5a52>About Us</h4><p data-v-1f2e5a52> We are students at the Faculty of Technical Sciences in Novi Sad <br data-v-1f2e5a52> trying to make this project for a course in Internet Software Architecture </p><p data-v-1f2e5a52> Made with <i class="fa fa-heart-o" data-v-1f2e5a52></i> by <a href="https://github.com/AnicaDjukic" data-v-1f2e5a52><i class="fa fa-github" data-v-1f2e5a52> Anica</i></a>, <a href="https://github.com/ABjelobaba" data-v-1f2e5a52><i class="fa fa-github" data-v-1f2e5a52> Aleksandar</i></a> and <a href="https://github.com/leakalmar" data-v-1f2e5a52><i class="fa fa-github" data-v-1f2e5a52> Lea</i></a></p><p data-v-1f2e5a52> You can find our project <a href="https://github.com/leakalmar/FishingAdventureService" data-v-1f2e5a52>here</a> . </p></div>',1),lt=[it];function st(e,t,n,o,c,r){return Object(a["F"])(),Object(a["h"])("section",null,lt)}var ut={name:"Footer"};n("8f1c");const dt=tt()(ut,[["render",st],["__scopeId","data-v-1f2e5a52"]]);var bt=dt,ht={components:{NavBar:rt,Footer:bt},mounted:function(){window.location.href.includes("auth")&&(document.getElementById("bar").style="display:none",document.getElementById("footer").style="display:none")}};n("d5ec");const ft=tt()(ht,[["render",o]]);var pt=ft,Ot=n("1da1"),mt=(n("96cf"),n("d3b7"),n("3ca3"),n("ddb0"),n("6c02")),gt=[{path:"/",name:"Home",component:function(){return n.e("chunk-73dafbd9").then(n.bind(null,"bb51"))},beforeEnter:function(){jt()}},{path:"/auth/:id",name:"ConfirmToken",component:function(){return n.e("chunk-1162162b").then(n.bind(null,"9901"))}},{path:"/profile",name:"profile",component:function(){return n.e("chunk-45d11442").then(n.bind(null,"c66d"))},beforeEnter:function(e,t,n){jt().then((function(e){void 0==e?n("/"):n()}))}},{path:"/search",name:"Search",beforeEnter:function(){jt()},component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-fe77485e"),n.e("chunk-c6739a70"),n.e("chunk-3856987e"),n.e("chunk-60a1ccae")]).then(n.bind(null,"2d3b"))},children:[{path:"",name:"SearchAll",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-fe77485e"),n.e("chunk-c6739a70"),n.e("chunk-3856987e"),n.e("chunk-60a1ccae")]).then(n.bind(null,"2d3b"))}},{path:"boats",name:"SearchBoats",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-fe77485e"),n.e("chunk-c6739a70"),n.e("chunk-3856987e"),n.e("chunk-60a1ccae")]).then(n.bind(null,"2d3b"))}},{path:"cottages",name:"SearchCottages",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-fe77485e"),n.e("chunk-c6739a70"),n.e("chunk-3856987e"),n.e("chunk-60a1ccae")]).then(n.bind(null,"2d3b"))}},{path:"adventures",name:"SearchAdventures",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-fe77485e"),n.e("chunk-c6739a70"),n.e("chunk-3856987e"),n.e("chunk-60a1ccae")]).then(n.bind(null,"2d3b"))}}]},{path:"/cottages",name:"MyCottages",component:function(){return Promise.all([n.e("chunk-fe77485e"),n.e("chunk-3856987e"),n.e("chunk-19bbd2e3")]).then(n.bind(null,"434c"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_VACATION_HOME_OWNER"!=e?n("/"):n()}))}},{path:"/client/currentReservations",name:"ClientCurrentReservations",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-10c7e4bb")]).then(n.bind(null,"4f3e"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_CLIENT"!=e?n("/"):n()}))}},{path:"/client/pastReservations",name:"ClientPastReservations",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-41d9085f")]).then(n.bind(null,"9540"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_CLIENT"!=e?n("/"):n()}))}},{path:"/client/subscriptions",name:"ClientSubscriptions",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-12f7d5ac")]).then(n.bind(null,"c32c"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_CLIENT"!=e?n("/"):n()}))}},{path:"/specialOffers",name:"Offers",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-331d75e0")]).then(n.bind(null,"d7e1"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_VACATION_HOME_OWNER"!=e&&"ROLE_BOAT_OWNER"!=e&&"ROLE_FISHING_INSTRUCTOR"!=e?n("/"):n()}))}},{path:"/calendar",name:"Calendar",component:function(){return n.e("chunk-6fa4def7").then(n.bind(null,"ac0f"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_VACATION_HOME_OWNER"!=e&&"ROLE_BOAT_OWNER"!=e&&"ROLE_FISHING_INSTRUCTOR"!=e?n("/"):n()}))}},{path:"/adventure",name:"ProfileAdventure",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-69b08594"),n.e("chunk-f7c4ae44")]).then(n.bind(null,"84c0"))},beforeEnter:function(){jt()}},{path:"/cottage",name:"ProfileCottage",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-69b08594"),n.e("chunk-0dc54e32")]).then(n.bind(null,"1b33"))},beforeEnter:function(){jt()}},{path:"/boat",name:"ProfileBoat",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-69b08594"),n.e("chunk-6ad50bff")]).then(n.bind(null,"d474"))},beforeEnter:function(){jt()}},{path:"/clientReviews",name:"AllReviews",component:function(){return n.e("chunk-7e11ccc5").then(n.bind(null,"8fb5"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_ADMIN"!=e?n("/"):n()}))}},{path:"/clientComplaints",name:"ClientComplaints",component:function(){return n.e("chunk-23cb1359").then(n.bind(null,"18f4"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_ADMIN"!=e?n("/"):n()}))}},{path:"/advertiserComplaints",name:"ReportsForAdmin",component:function(){return n.e("chunk-e1e74576").then(n.bind(null,"cd49"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_ADMIN"!=e?n("/"):n()}))}},{path:"/deleteRequests",name:"DeleteRequests",component:function(){return n.e("chunk-09ee1a4a").then(n.bind(null,"6b70"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_ADMIN"!=e?n("/"):n()}))}},{path:"/users",name:"AllUsers",component:function(){return n.e("chunk-2e53f58d").then(n.bind(null,"a010"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_ADMIN"!=e?n("/"):n()}))}},{path:"/registrationRequests",name:"RegistrationRequests",component:function(){return n.e("chunk-2832d883").then(n.bind(null,"6b14"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_ADMIN"!=e?n("/"):n()}))}},{path:"/fishingAdventures",name:"MyAdventures",component:function(){return n.e("chunk-76012225").then(n.bind(null,"4a96"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_FISHING_INSTRUCTOR"!=e?n("/"):n()}))}},{path:"/reservations",name:"Reservations",component:function(){return Promise.all([n.e("chunk-a64cef48"),n.e("chunk-2964e9ae")]).then(n.bind(null,"3f90"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_FISHING_INSTRUCTOR"!=e&&"ROLE_VACATION_HOME_OWNER"!=e&&"ROLE_BOAT_OWNER"!=e?n("/"):n()}))}},{path:"/boats",name:"MyBoats",component:function(){return Promise.all([n.e("chunk-fe77485e"),n.e("chunk-c6739a70"),n.e("chunk-6f4f5344")]).then(n.bind(null,"af11"))},beforeEnter:function(e,t,n){jt().then((function(e){"ROLE_BOAT_OWNER"!=e?n("/"):n()}))}}],kt=Object(mt["a"])({history:Object(mt["b"])("/"),routes:gt});function jt(){return vt.apply(this,arguments)}function vt(){return vt=Object(Ot["a"])(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return t=void 0,e.next=3,yt().then((function(e){t=void 0!=e.data&&""!=e.data?e.data:void 0})).catch((function(){It().then((function(e){void 0!=e.data&&""!=e.data?(localStorage.clear(),localStorage.setItem("jwt",e.data.accessToken),localStorage.setItem("refreshToken",e.data.refreshToken),t=e.data.roles[0]):(localStorage.clear(),t=void 0)})).catch((function(){t=void 0}))}));case 3:return e.abrupt("return",t);case 4:case"end":return e.stop()}}),e)}))),vt.apply(this,arguments)}var Rt=kt;function yt(){return Et.apply(this,arguments)}function Et(){return Et=Object(Ot["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,Ze.a.get("http://localhost:8080/users/getRole",{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.jwt}});case 3:return e.abrupt("return",e.sent);case 6:e.prev=6,e.t0=e["catch"](0),console.log("Token expired...");case 9:case"end":return e.stop()}}),e,null,[[0,6]])}))),Et.apply(this,arguments)}function It(){return wt.apply(this,arguments)}function wt(){return wt=Object(Ot["a"])(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){while(1)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,Ze.a.get("http://localhost:8080/auth/refreshToken",{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(e){return console.log("Session successfully refreshed."),e}));case 3:return e.abrupt("return",e.sent);case 6:e.prev=6,e.t0=e["catch"](0),localStorage.clear(),console.log("Refreshing token failed.");case 10:case"end":return e.stop()}}),e,null,[[0,6]])}))),wt.apply(this,arguments)}var _t=n("130e"),Nt=n("ef2e"),Ct=(n("dfc8"),n("7b37")),Lt=n("320b"),At=n("af56");Object(a["e"])(pt).use(Lt["a"],{load:{key:"AIzaSyDS-rs0HVP_awX2aP1u49VhJzJcL_K3lko",libraries:["places"],language:"en"}}).use(_t["a"],Ze.a).use(Rt).use(Ct["a"],{componentPrefix:"vc"}).component("Datepicker",Nt["a"]).use(At["a"],{position:"bottom"}).mount("#app")},"8f1c":function(e,t,n){"use strict";n("25fd")},"8f95":function(e,t,n){"use strict";n("df34")},9344:function(e,t,n){},d5ec:function(e,t,n){"use strict";n("9344")},df34:function(e,t,n){},f6a8:function(e,t,n){"use strict";n("3cdd")}});
//# sourceMappingURL=app.558890a7.js.map