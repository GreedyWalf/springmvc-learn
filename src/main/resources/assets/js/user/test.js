/**
 * 定义test模块，test模块中引用了自定义的index模块，index模块中将indexObj这个对象暴露出来，供外部调用
 * 即可以直接调用index模块中定义的index方法；
 */
layui.define(['index'], function(exports){
    //加载自定义的index模块
    var index = layui.index;

    index.index("测试一下~~");
    exports('test', {});
});
