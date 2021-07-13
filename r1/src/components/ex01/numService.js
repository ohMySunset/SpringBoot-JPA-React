
/*로직처리는 따로 파일에 정의하고 컴포넌트에서 import 하는 방식으로 처리함.
*  단위 기능을 가져올 경우 : export const
*  전체 기능을 가져올 경우 : export default
*/
export const makeNum = () => {

    const value =Math.random()*100

    return  Math.ceil(value)

}

export default makeNum
