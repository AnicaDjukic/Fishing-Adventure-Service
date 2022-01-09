import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios'
const routes = [{
    path: '/',
    name: 'Home',
    component: () =>
        import ('../views/Home.vue'),
    beforeEnter: () => {
        checkAuthentification();
    }
}, {
    path: '/auth/:id',
    name: 'ConfirmToken',
    component: () =>
        import ('../ConfirmToken.vue'),
}, {
    path: '/profile',
    name: 'profile',
    component: () =>
        import ('../views/Profile.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if (response == undefined) {
                next("/")
            } else {
                next();
            }
        })
    }
}, {
    path: '/search',
    name: 'Search',
    beforeEnter: () => {
        checkAuthentification();
    },
    component: () =>
        import ('../views/Search.vue'),
    children: [{
        path: '',
        name: 'SearchAll',
        component: () =>
            import ('../views/Search.vue')
    }, {
        path: 'boats',
        name: 'SearchBoats',
        component: () =>
            import ('../views/Search.vue')
    }, {
        path: 'cottages',
        name: 'SearchCottages',
        component: () =>
            import ('../views/Search.vue')
    }, {
        path: 'adventures',
        name: 'SearchAdventures',
        component: () =>
            import ('../views/Search.vue')
    }]
}, {
    path: '/cottages',
    name: 'MyCottages',
    component: () =>
        import ('../views/MyCottages.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if (response != "ROLE_VACATION_HOME_OWNER") {
                next("/")
            } else {
                next();
            }
        })
    }
}, {
    path: '/client/currentReservations',
    name: 'ClientCurrentReservations',
    component: () =>
        import ('../views/ClientCurrentReservations.vue')
}, {
    path: '/client/pastReservations',
    name: 'ClientPastReservations',
    component: () =>
        import ('../views/ClientPastReservations.vue')
}, {
    path: '/client/subscriptions',
    name: 'ClientSubscriptions',
    component: () =>
        import ('../views/ClientSubscriptions.vue')
}, {
    path: '/specialOffers',
    name: 'Offers',
    component: () =>
        import ('../views/Offers.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if (response != "ROLE_VACATION_HOME_OWNER" && response != "ROLE_BOAT_OWNER" && response != "ROLE_FISHING_INSTRUCTOR") {
                next("/")
            } else {
                next();
            }
        })
    }
}, {
    path: '/calendar',
    name: 'Calendar',
    component: () =>
        import ('../views/Calendar.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if (response != "ROLE_VACATION_HOME_OWNER" && response != "ROLE_BOAT_OWNER") {
                next("/")
            } else {
                next();
            }
        })
    }
}, {
    path: '/fishingAdventure',
    name: 'ProfileAdventure',
    component: () =>
        import ('../views/ProfileAdventure.vue')
}, {
    path: '/cottage',
    name: 'ProfileCottage',
    component: () =>
        import ('../views/ProfileCottage.vue')
}, {
    path: '/boat',
    name: 'ProfileBoat',
    component: () =>
        import ('../views/ProfileBoat.vue')
}, {
    path: '/admin/profile',
    name: 'AdminProfile',
    component: () =>
        import ('../views/AdminProfile.vue')
}, {
    path: '/users',
    name: 'AllUsers',
    component: () =>
        import ('../views/AllUsers.vue')
}, {
    path: '/registrationRequests',
    name: 'RegistrationRequests',
    component: () =>
        import ('../views/RegistrationRequests.vue')
}, {
    path: '/fishingAdventures',
    name: 'MyAdventures',
    component: () =>
        import ('../views/MyAdventures.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if (response != "ROLE_FISHING_INSTRUCTOR") {
                next("/")
            } else {
                next();
            }
        })
    }
}, {
    path: '/reservations',
    name: 'Reservations',
    component: () =>
        import ('../views/Reservations.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if ((response != "ROLE_FISHING_INSTRUCTOR") && (response != "ROLE_VACATION_HOME_OWNER") && (response != "ROLE_BOAT_OWNER")) {
                next("/")
            } else {
                next();
            }
        })
    }
}, {
    path: '/boats',
    name: 'MyBoats',
    component: () =>
        import ('../views/MyBoats.vue'),
    beforeEnter: (to, from, next) => {
        checkAuthentification().then(response => {
            if (response != "ROLE_BOAT_OWNER") {
                next("/")
            } else {
                next();
            }
        })
    }
}]
const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})
async function checkAuthentification() {
    let retVal = undefined;
    await checkRole().then(response => {
        if (response.data != undefined && response.data != "") {
            retVal = response.data;
        } else {
            retVal = undefined;
        }
    }).catch(() => {
        refreshToken().then(response => {
            if (response.data != undefined && response.data != "") {
                localStorage.clear();
                localStorage.setItem("jwt", response.data.accessToken);
                localStorage.setItem("refreshToken", response.data.refreshToken);
                retVal = response.data.roles[0];
            } else {
                localStorage.clear();
                retVal = undefined;
            }
        }).catch(() => {
            retVal = undefined;
        })
    })
    return retVal;
}
export default router
async function checkRole() {
    try {
        return await axios.get("http://localhost:8080/users/getRole", {
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.jwt,
            },
        })
    } catch (err) {
        console.log("Token expired...");
    }
}
async function refreshToken() {
    try {
        return await axios.get("http://localhost:8080/auth/refreshToken", {
            headers: {
                "Access-Control-Allow-Origin": "http://localhost:8080",
                Authorization: "Bearer " + localStorage.refreshToken,
            },
        }).then(() => {
            this.$toast.show("Session successfully refreshed.");
        })
    } catch {
        localStorage.clear();
        console.log("Refreshing token failed.")
        this.$toast.show("Session expired.");
    }
}