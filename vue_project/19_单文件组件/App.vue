<template>
    <div>
        <School/>
        <Student/>
    </div>
</template>

<script>
//引入组件
import School from './School.Vue'
import Student from './Student.Vue'

export default {
    name: 'App',
    components: {
        School,
        Student
    }
}
</script>