<template>
    <div>
       
    </div>
</template>

<script>

export default {
    name: '',
    data() {
        return {
            searchObj:{}
        }
    },
    // props:{
    //     searchObj:Object,
    // },
    components: {},
    created() { },
    mounted() { },
    computed: {},
    methods: {
        

    fetchPageList(searchobj) {
        this.$emit('secrchInfo',searchobj)
    },
    },
}

</script>
<style scoped>

</style>
