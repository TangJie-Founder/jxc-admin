/*需要权限控制的路由表*/
import purchaseRouter from '@/router/modules/purchase'
import sellRouter from '@/router/modules/sell'
import stockRouter from '@/router/modules/stock'
import systemRouter from '@/router/modules/system'


const authorityRoutes = [
    purchaseRouter,
    sellRouter,
    stockRouter,
    systemRouter,
]

export default authorityRoutes
