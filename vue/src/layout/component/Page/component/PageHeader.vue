<template>
    <div class="page-header">
        <h1 class="page-header-title">{{ title }}</h1>

        <el-breadcrumb>
            <el-breadcrumb-item v-for="(item,index) in data" :key="item.path">
                <span :class="{'no-redirect': index !== data.length - 1}">{{ item.meta.title }}</span>
            </el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>

<script>
export default {
    name: "PageHeader",

    data: () => ({data: [], title: ''}),

    watch: {
        $route: {
            immediate: true,
            handler(to) {
                const {path, meta: {title}, matched} = to
                if (!path.startsWith('/redirect')) {
                    this.title = title
                    this.data = matched.filter(item => item.meta.title)
                }
            }
        }
    }
}
</script>

<style lang="scss">
@import "~@/asset/style/variables.scss";

.page-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: nowrap;
    height: $page-header-height;
    font-size: 14px;
    padding: 16px $page-view-margin 0 $page-view-margin;

    > .el-breadcrumb .no-redirect {
        color: #909399;
        cursor: text;
    }

    &-title {
        margin: 4px 0;
        color: #303133;
        font-weight: 400;
        font-size: 24px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
    }
}
</style>
