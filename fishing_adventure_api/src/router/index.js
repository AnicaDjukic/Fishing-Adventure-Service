import { createRouter, createWebHistory } from 'vue-router'
const routes = [{
    path: '/',
    name: 'Home',
    component: () =>
        import ('../views/Home.vue')
}, {
    path: '/profile',
    name: 'profile',
    component: () =>
        import ('../views/Profile.vue')
}, {
    path: '/search',
    name: 'Search',
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
        import ('../views/MyCottages.vue')
}, {
    path: '/reservations',
    name: 'HomeOwnerReservations',
    component: () =>
        import ('../views/HomeOwnerReservations.vue')
}, {
    path: '/specialOffers',
    name: 'HomeOwnerSpecialOffers',
    component: () =>
        import ('../views/HomeOwnerSpecialOffers.vue')
}, {
    path: '/calendar',
    name: 'HomeOwnerCalendar',
    component: () =>
        import ('../views/HomeOwnerCalendar.vue')
}, {
    path: '/fishingAdventure',
    name: 'FishingAdventure',
    component: () =>
        import ('../views/FishingAdventure.vue')
}, {
    path: '/cottage',
    name: 'CottageProfile',
    component: () =>
        import ('../views/CottageProfile.vue')
}]
const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})
export default router