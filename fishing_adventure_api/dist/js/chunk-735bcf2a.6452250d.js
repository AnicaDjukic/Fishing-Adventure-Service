(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-735bcf2a"],{"77e3":function(e,t,n){},a010:function(e,t,n){"use strict";n.r(t);n("b0c0");var c=n("7a23"),l=function(e){return Object(c["I"])("data-v-1a16d1df"),e=e(),Object(c["G"])(),e},a=l((function(){return Object(c["i"])("div",{class:"title"},[Object(c["i"])("h1",null,"All users"),Object(c["i"])("i",{class:"far fa-users fa-3x",style:{"font-family":"'Font Awesome 5 Pro'"}})],-1)})),i={style:{"background-color":"#212529",padding:"15px","text-align":"-webkit-center"}},r={class:"container w-100 row row-cols-1 row-cols-sm-1 row-cols-md-4",style:{"justify-content":"space-evenly","align-items":"center"}},s=l((function(){return Object(c["i"])("div",{class:"col-md-2"},[Object(c["i"])("button",{type:"button",class:"btn btn-outline-primary text-nowrap me-2","data-bs-toggle":"modal"}," Add new admin ")],-1)})),o={class:"col-md-3"},b={class:"col-md-4"},u={style:{display:"flex"}},d=l((function(){return Object(c["i"])("div",{class:"nav-item dropdown"},[Object(c["i"])("a",{class:"nav-link dropdown-toggle",href:"#",id:"navbarScrollingDropdown",role:"button","data-bs-toggle":"dropdown","aria-expanded":"false",style:{color:"white",margin:"-2% 0"}}," User type "),Object(c["i"])("ul",{class:"dropdown-menu text-center dropdown-menu-dark","aria-labelledby":"navbarScrollingDropdown"},[Object(c["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(c["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(c["i"])("label",{style:{"font-size":"17px"}},"Clients")]),Object(c["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(c["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(c["i"])("label",{style:{"font-size":"17px"}},"Cottage owners")]),Object(c["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(c["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(c["i"])("label",{style:{"font-size":"17px"}},"Boat owners")]),Object(c["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(c["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(c["i"])("label",{style:{"font-size":"17px"}},"Fishing instructors")]),Object(c["i"])("span",{class:"dropdown-item",style:{"text-align":"left"}},[Object(c["i"])("input",{type:"checkbox",style:{"margin-right":"10px","margin-left":"5px"}}),Object(c["i"])("label",{style:{"font-size":"17px"}},"Administrators")])])],-1)})),O=[d],f={class:"table-users"},j=l((function(){return Object(c["i"])("th",null,"Role",-1)})),p=Object(c["l"])("Name "),h=l((function(){return Object(c["i"])("i",{class:"fa fa-sort"},null,-1)})),y=[p,h],m=Object(c["l"])("Surname "),g=l((function(){return Object(c["i"])("i",{class:"fa fa-sort"},null,-1)})),x=[m,g],w=Object(c["l"])("Email "),k=l((function(){return Object(c["i"])("i",{class:"fa fa-sort"},null,-1)})),v=[w,k],R=l((function(){return Object(c["i"])("th",null,null,-1)})),A=["user"],E={key:0,class:"fas fa-user fa-lg",style:{color:"#003148"},"aria-hidden":"true"},F={key:1,class:"fas fa-cog fa-lg",style:{color:"#003148"},"aria-hidden":"true"},U={key:2,class:"fas fa-fish fa-lg",style:{color:"#003148"},"aria-hidden":"true"},_={key:3,class:"fas fa-home fa-lg",style:{color:"#003148"},"aria-hidden":"true"},C={key:4,class:"fas fa-anchor fa-lg",style:{color:"#003148"},"aria-hidden":"true"},N={key:0,class:"black-btn",disabled:""},S=l((function(){return Object(c["i"])("i",{class:"fa fa-trash-o","aria-hidden":"true"},null,-1)})),B=Object(c["l"])(" Delete"),I=[S,B],L={key:1,class:"black-btn"},T=l((function(){return Object(c["i"])("i",{class:"fa fa-trash-o","aria-hidden":"true"},null,-1)})),z=Object(c["l"])(" Delete"),D=[T,z],M=l((function(){return Object(c["i"])("div",{class:"loyalty-button"},[Object(c["i"])("button",{type:"button",class:"btn btn-secondary",style:{"background-color":"#DAA520",color:"black","font-size":"105%"}},[Object(c["i"])("i",{class:"fas fa-crown"}),Object(c["l"])(" Loyalty program ")])],-1)}));function W(e,t,n,l,d,p){return Object(c["F"])(),Object(c["h"])("div",null,[a,Object(c["i"])("div",i,[Object(c["i"])("div",r,[s,Object(c["i"])("div",o,[Object(c["ab"])(Object(c["i"])("input",{class:"form-control me-2",type:"search",placeholder:"Search","aria-label":"Search","onUpdate:modelValue":t[0]||(t[0]=function(t){return e.searchText=t}),onKeyup:t[1]||(t[1]=function(){return p.searchUsers&&p.searchUsers.apply(p,arguments)}),onClick:t[2]||(t[2]=function(){return p.searchUsers&&p.searchUsers.apply(p,arguments)})},null,544),[[c["W"],e.searchText]])]),Object(c["i"])("div",b,[Object(c["i"])("div",u,[Object(c["i"])("div",{class:"rating-div form-control",style:{"min-width":"115px"},onClick:t[3]||(t[3]=function(){return p.searchUsers&&p.searchUsers.apply(p,arguments)})},O)])])])]),Object(c["i"])("div",null,[Object(c["i"])("table",f,[Object(c["i"])("thead",null,[Object(c["i"])("tr",null,[j,Object(c["i"])("th",{onClick:t[4]||(t[4]=function(){return e.sortByName&&e.sortByName.apply(e,arguments)}),id:"name-th"},y),Object(c["i"])("th",{onClick:t[5]||(t[5]=function(){return e.sortBySurname&&e.sortBySurname.apply(e,arguments)}),id:"surname-th"},x),Object(c["i"])("th",{onClick:t[6]||(t[6]=function(){return e.sortByEmail&&e.sortByEmail.apply(e,arguments)}),id:"email-th"},v),R])]),Object(c["i"])("tbody",null,[(Object(c["F"])(!0),Object(c["h"])(c["a"],null,Object(c["M"])(e.users,(function(e){return Object(c["F"])(),Object(c["h"])("tr",{key:e.id,user:e},[Object(c["i"])("td",null,["ROLE_CLIENT"==e.role?(Object(c["F"])(),Object(c["h"])("i",E)):"ROLE_ADMIN"==e.role?(Object(c["F"])(),Object(c["h"])("i",F)):"ROLE_FISHING_INSTRUCTOR"==e.role?(Object(c["F"])(),Object(c["h"])("i",U)):"ROLE_VACATION_HOME_OWNER"==e.role?(Object(c["F"])(),Object(c["h"])("i",_)):"ROLE_BOAT_OWNER"==e.role?(Object(c["F"])(),Object(c["h"])("i",C)):Object(c["g"])("",!0)]),Object(c["i"])("td",null,Object(c["R"])(e.name),1),Object(c["i"])("td",null,Object(c["R"])(e.surname),1),Object(c["i"])("td",null,Object(c["R"])(e.email),1),Object(c["i"])("td",null,["ROLE_ADMIN"==e.role?(Object(c["F"])(),Object(c["h"])("button",N,I)):(Object(c["F"])(),Object(c["h"])("button",L,D))])],8,A)})),128))])]),M])])}var G=n("bc3a"),H=n.n(G);H.a.defaults.baseURL="/";var J={data:function(){return{searchText:"",searchResults:[],users:[]}},mounted:function(){var e=this;H.a.get("/users/getAllUsers",{headers:{"Access-Control-Allow-Origin":"/",Authorization:"Bearer "+localStorage.refreshToken}}).then((function(t){e.users=t.data}))},methods:{searchUsers:function(){}}},V=(n("f833"),n("f94c"),n("6b0d")),K=n.n(V);const P=K()(J,[["render",W],["__scopeId","data-v-1a16d1df"]]);t["default"]=P},e390:function(e,t,n){},f833:function(e,t,n){"use strict";n("e390")},f94c:function(e,t,n){"use strict";n("77e3")}}]);
//# sourceMappingURL=chunk-735bcf2a.6452250d.js.map