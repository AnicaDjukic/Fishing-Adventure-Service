(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2832d883"],{"18df":function(e,t,a){},"2d72":function(e,t,a){},"511e":function(e,t,a){"use strict";a("530a")},"51d3":function(e,t,a){"use strict";a("18df")},"530a":function(e,t,a){},"5e64":function(e,t,a){"use strict";a("94a2")},"6b14":function(e,t,a){"use strict";a.r(t);a("b0c0");var s=a("7a23"),r=function(e){return Object(s["I"])("data-v-21e39d5a"),e=e(),Object(s["G"])(),e},i=r((function(){return Object(s["i"])("div",{class:"title"},[Object(s["i"])("h1",null,"Registration requests"),Object(s["i"])("i",{class:"fas fa-envelope-open-text fa-3x",style:{"font-family":"'Font Awesome 5 Pro'"}})],-1)})),n={style:{"background-color":"#212529",padding:"15px","text-align":"-webkit-center"}},c={class:"container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4",style:{"justify-content":"space-evenly","align-items":"center"}},o={class:"col-md-3"},l={class:"col-md-4"},d={style:{display:"flex"}},u=r((function(){return Object(s["i"])("div",{class:"nav-item dropdown"},[Object(s["i"])("a",{class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false",style:{color:"white",margin:"-2% 0"}}," User type "),Object(s["i"])("ul",{class:"dropdown-menu text-center dropdown-menu-dark","aria-labelledby":"navbarScrollingDropdown"},[Object(s["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(s["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(s["i"])("label",{style:{"font-size":"17px"}},"Cottage owners")]),Object(s["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(s["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(s["i"])("label",{style:{"font-size":"17px"}},"Boat owners")]),Object(s["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(s["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(s["i"])("label",{style:{"font-size":"17px"}},"Fishing instructors")])])],-1)})),b=[u],j={class:"table-users"},O=r((function(){return Object(s["i"])("th",null,"Advertiser type",-1)})),f=Object(s["l"])(" Name "),p=r((function(){return Object(s["i"])("i",{class:"fa fa-sort"},null,-1)})),h=[f,p],m=Object(s["l"])(" Surname "),y=r((function(){return Object(s["i"])("i",{class:"fa fa-sort"},null,-1)})),g=[m,y],v=Object(s["l"])(" Email "),R=r((function(){return Object(s["i"])("i",{class:"fa fa-sort"},null,-1)})),w=[v,R],x=["user","data-bs-target"],k={key:0,class:"fas fa-user fa-lg",style:{color:"#003148"},"aria-hidden":"true"},F={key:1,class:"fas fa-cog fa-lg",style:{color:"#003148"},"aria-hidden":"true"},T={key:2,class:"fas fa-fish fa-lg",style:{color:"#003148"},"aria-hidden":"true"},q={key:3,class:"fas fa-home fa-lg",style:{color:"#003148"},"aria-hidden":"true"},C={key:4,class:"fas fa-anchor fa-lg",style:{color:"#003148"},"aria-hidden":"true"};function A(e,t,a,r,u,f){var p=Object(s["O"])("registration-request"),m=Object(s["O"])("ReasonForRejectionModal");return Object(s["F"])(),Object(s["h"])("div",null,[i,Object(s["i"])("div",n,[Object(s["i"])("div",c,[Object(s["i"])("div",o,[Object(s["ab"])(Object(s["i"])("input",{class:"form-control me-2",type:"search",placeholder:"Search","aria-label":"Search","onUpdate:modelValue":t[0]||(t[0]=function(t){return e.searchText=t}),onKeyup:t[1]||(t[1]=function(){return f.searchUsers&&f.searchUsers.apply(f,arguments)}),onClick:t[2]||(t[2]=function(){return f.searchUsers&&f.searchUsers.apply(f,arguments)})},null,544),[[s["W"],e.searchText]])]),Object(s["i"])("div",l,[Object(s["i"])("div",d,[Object(s["i"])("div",{class:"rating-div form-control",style:{"min-width":"115px"},onClick:t[3]||(t[3]=function(){return f.searchUsers&&f.searchUsers.apply(f,arguments)})},b)])])])]),Object(s["i"])("div",null,[Object(s["i"])("table",j,[Object(s["i"])("thead",null,[Object(s["i"])("tr",null,[O,Object(s["i"])("th",{onClick:t[4]||(t[4]=function(){return e.sortByName&&e.sortByName.apply(e,arguments)}),id:"name-th"},h),Object(s["i"])("th",{onClick:t[5]||(t[5]=function(){return e.sortBySurname&&e.sortBySurname.apply(e,arguments)}),id:"surname-th"},g),Object(s["i"])("th",{onClick:t[6]||(t[6]=function(){return e.sortByEmail&&e.sortByEmail.apply(e,arguments)}),id:"email-th"},w)])]),Object(s["i"])("tbody",null,[(Object(s["F"])(!0),Object(s["h"])(s["a"],null,Object(s["M"])(e.users,(function(e){return Object(s["F"])(),Object(s["h"])("tr",{key:e.id,user:e,"data-bs-toggle":"modal","data-bs-target":"#registration-request"+e.id},[Object(s["i"])("td",null,["ROLE_CLIENT"==e.userType?(Object(s["F"])(),Object(s["h"])("i",k)):"ROLE_ADMIN"==e.userType?(Object(s["F"])(),Object(s["h"])("i",F)):"ROLE_FISHING_INSTRUCTOR"==e.userType?(Object(s["F"])(),Object(s["h"])("i",T)):"ROLE_VACATION_HOME_OWNER"==e.userType?(Object(s["F"])(),Object(s["h"])("i",q)):"ROLE_BOAT_OWNER"==e.userType?(Object(s["F"])(),Object(s["h"])("i",C)):Object(s["g"])("",!0)]),Object(s["i"])("td",null,Object(s["R"])(e.name),1),Object(s["i"])("td",null,Object(s["R"])(e.surname),1),Object(s["i"])("td",null,Object(s["R"])(e.email),1),Object(s["m"])(p,{id:"registration-request"+e.id,user:e,onRejectRequest:f.rejectRequest},null,8,["id","user","onRejectRequest"])],8,x)})),128))])])]),Object(s["m"])(m,{id:"ReasonForRejectionModal",email:e.email},null,8,["email"])])}a("a4d3"),a("e01a");var E=function(e){return Object(s["I"])("data-v-37ed9dc8"),e=e(),Object(s["G"])(),e},I=["id"],_={class:"modal-dialog modal-dialog-centered"},M={class:"modal-content"},N=E((function(){return Object(s["i"])("div",{class:"modal-header"},[Object(s["i"])("h3",{style:{"font-size":"23px",margin:"0 0 0 auto",color:"white"}}," Registration request "),Object(s["i"])("button",{type:"button",class:"btn-close","data-bs-dismiss":"modal"},[Object(s["i"])("i",{class:"fas fa-times fa-lg"})])],-1)})),S={class:"modal-body"},B={key:0,style:{"margin-bottom":"20px"}},U=Object(s["l"])("Fishing instructor "),L=E((function(){return Object(s["i"])("i",{class:"fas fa-fish fa-lg","aria-hidden":"true"},null,-1)})),z=[U,L],G={key:1,style:{"margin-bottom":"20px"}},W=Object(s["l"])("Cottage owner "),D=E((function(){return Object(s["i"])("i",{class:"fas fa-home fa-lg","aria-hidden":"true"},null,-1)})),H=[W,D],V={key:2,style:{"margin-bottom":"20px"}},J=Object(s["l"])("Boat owner "),K=E((function(){return Object(s["i"])("i",{class:"fas fa-anchor fa-lg","aria-hidden":"true"},null,-1)})),P=[J,K],Y={class:"reg-request-body",style:{color:"white"}},$={style:{display:"inline-flex",justify:"space-between",width:"100%","justify-content":"space-between"}},Q=Object(s["k"])('<div data-v-37ed9dc8><div class="rr-row" data-v-37ed9dc8><span class="rr-left" data-v-37ed9dc8> Email: </span></div><div class="rr-row" data-v-37ed9dc8><span class="rr-left" data-v-37ed9dc8> Name: </span></div><div class="rr-row" data-v-37ed9dc8><span class="rr-left" data-v-37ed9dc8> Telephone: </span></div><div class="rr-row" data-v-37ed9dc8><span class="rr-left" data-v-37ed9dc8> Country: </span></div><div class="rr-row" data-v-37ed9dc8><span class="rr-left" data-v-37ed9dc8> Address: </span></div></div>',1),X={class:"rr-row"},Z={class:"rr-right"},ee={class:"rr-row"},te={class:"rr-right"},ae={class:"rr-row"},se={class:"rr-right"},re={class:"rr-row"},ie={class:"rr-right"},ne={class:"rr-row"},ce={class:"rr-right"},oe=E((function(){return Object(s["i"])("div",{class:"rr-row"},"Reason for joining:",-1)})),le={class:"rr-reason-txt"},de={class:"modal-footer"};function ue(e,t,a,r,i,n){return Object(s["F"])(),Object(s["h"])("div",{class:"modal fade dark",id:a.id,tabindex:"-1","aria-labelledby":"LogInModalLabel","aria-hidden":"true"},[Object(s["i"])("div",_,[Object(s["i"])("div",M,[N,Object(s["i"])("div",S,["FISHING_INSTRUCTOR"==a.user.userType?(Object(s["F"])(),Object(s["h"])("h5",B,z)):"COTTAGE_OWNER"==a.user.userType?(Object(s["F"])(),Object(s["h"])("h5",G,H)):"BOAT_OWNER"==a.user.userType?(Object(s["F"])(),Object(s["h"])("h5",V,P)):Object(s["g"])("",!0),Object(s["i"])("div",Y,[Object(s["i"])("div",$,[Q,Object(s["i"])("div",null,[Object(s["i"])("div",X,[Object(s["i"])("span",Z,Object(s["R"])(a.user.email),1)]),Object(s["i"])("div",ee,[Object(s["i"])("span",te,Object(s["R"])(a.user.name)+" "+Object(s["R"])(a.user.surname),1)]),Object(s["i"])("div",ae,[Object(s["i"])("span",se,Object(s["R"])(a.user.phoneNumber),1)]),Object(s["i"])("div",re,[Object(s["i"])("span",ie,Object(s["R"])(a.user.country),1)]),Object(s["i"])("div",ne,[Object(s["i"])("span",ce,Object(s["R"])(a.user.street)+", "+Object(s["R"])(a.user.city),1)])])]),oe,Object(s["i"])("div",le,Object(s["R"])(a.user.description),1)])]),Object(s["i"])("div",de,[Object(s["i"])("button",{type:"button",class:"btn btn-outline-primary",style:{width:"20%"},onClick:t[0]||(t[0]=function(){return n.approveRequest&&n.approveRequest.apply(n,arguments)})}," Approve "),Object(s["i"])("button",{type:"button",class:"btn btn-outline-primary",style:{width:"20%"},onClick:t[1]||(t[1]=function(){return n.rejectRequest&&n.rejectRequest.apply(n,arguments)}),"data-bs-dismiss":"modal"}," Reject ")])])])],8,I)}var be=a("bc3a"),je=a.n(be),Oe={name:"RequestForRegistration",props:["id","user"],emits:["rejectRequest"],data:function(){return{}},mounted:function(){},methods:{approveRequest:function(){je.a.get("http://localhost:8080/users/approveRegistrationRequest/"+this.user.email,{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then(window.location.reload())},rejectRequest:function(){this.$emit("rejectRequest",this.user.email)}}},fe=(a("c04f"),a("511e"),a("6b0d")),pe=a.n(fe);const he=pe()(Oe,[["render",ue],["__scopeId","data-v-37ed9dc8"]]);var me=he,ye=function(e){return Object(s["I"])("data-v-4a2d7e5e"),e=e(),Object(s["G"])(),e},ge={class:"modal-dialog modal-dialog-centered"},ve={class:"modal-content",style:{height:"20.5rem"}},Re={class:"modal-header"},we=ye((function(){return Object(s["i"])("h3",{style:{"font-size":"23px"}}," Reason for rejection ",-1)})),xe=ye((function(){return Object(s["i"])("i",{class:"fas fa-times fa-lg"},null,-1)})),ke=[xe],Fe={class:"modal-body"},Te={style:{color:"red","margin-top":"1rem"}},qe={class:"modal-footer steps-div"};function Ce(e,t,a,r,i,n){return Object(s["F"])(),Object(s["h"])("div",{class:"modal fade dark",id:"ReasonForRejectionModal","aria-labelledby":"OfferModalLabel","aria-hidden":"true","data-bs-backdrop":"static","data-bs-keyboard":"false",onShow:t[3]||(t[3]=function(){return n.closeModal&&n.closeModal.apply(n,arguments)})},[Object(s["i"])("div",ge,[Object(s["i"])("div",ve,[Object(s["i"])("div",Re,[we,Object(s["i"])("button",{type:"button",class:"btn-close","data-bs-dismiss":"modal",onClick:t[0]||(t[0]=function(){return n.closeModal&&n.closeModal.apply(n,arguments)})},ke)]),Object(s["i"])("div",Fe,[Object(s["ab"])(Object(s["i"])("textarea",{class:"login-inputs-textarea",placeholder:"Enter reason here..","onUpdate:modelValue":t[1]||(t[1]=function(t){return e.reason=t}),style:{"max-height":"10rem","margin-top":"20px"}},null,512),[[s["W"],e.reason]]),Object(s["i"])("h6",Te,[Object(s["i"])("b",null,Object(s["R"])(e.error),1)])]),Object(s["i"])("div",qe,[Object(s["i"])("button",{type:"button",class:"btn btn-outline-primary",onClick:t[2]||(t[2]=function(){return n.confirmRejection&&n.confirmRejection.apply(n,arguments)})}," Confirm rejection ")])])])],32)}var Ae={components:{},props:["email"],name:"ReasonForRejectionModal",data:function(){return{reason:"",error:""}},methods:{closeModal:function(){this.reason="",this.error=""},confirmRejection:function(){""==this.reason?this.error="You must enter a reason for rejection.":je.a.get("http://localhost:8080/users/rejectRegistrationRequest?email="+this.email+"&reason="+this.reason,{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then(window.location.reload())}}};a("5e64");const Ee=pe()(Ae,[["render",Ce],["__scopeId","data-v-4a2d7e5e"]]);var Ie=Ee,_e={components:{"registration-request":me,ReasonForRejectionModal:Ie},data:function(){return{searchText:"",searchResults:[],selectedUser:void 0,users:[],email:""}},mounted:function(){var e=this;je.a.get("http://localhost:8080/users/getAllRegistrationRequests",{headers:{"Access-Control-Allow-Origin":"http://localhost:8080",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.users=t.data,console.log(e.users)}))},methods:{searchUsers:function(){},showRequest:function(e){this.selectedUser=e},rejectRequest:function(e){this.email=e;var t=document.getElementById("ReasonForRejectionModal"),a=window.bootstrap.Modal.getOrCreateInstance(t);a.show()}}};a("51d3"),a("b866");const Me=pe()(_e,[["render",A],["__scopeId","data-v-21e39d5a"]]);t["default"]=Me},"94a2":function(e,t,a){},b866:function(e,t,a){"use strict";a("2d72")},bd64:function(e,t,a){},c04f:function(e,t,a){"use strict";a("bd64")}}]);
//# sourceMappingURL=chunk-2832d883.87dd4136.js.map