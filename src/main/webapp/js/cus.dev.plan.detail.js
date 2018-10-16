$(function () {
    var sid = $('#saleChanceId').val();
    var devResult = $("#devResult").val();
    // 表格初始化
    $('#dg').edatagrid({
        url: ctx + "/cusDevPlan/queryCusDevPlansByParams?sid="+sid,
        saveUrl:ctx + "/cusDevPlan/saveOrUpdateCusDevPlans?sid="+sid,
        updateUrl:ctx + "/cusDevPlan/saveOrUpdateCusDevPlans?sid="+sid,
    });
    if (devResult==2 || devResult==3 ){
        // 隐藏工具条
        $('#toolbar').hide();
        // 使表格不可编辑
        $('#dg').edatagrid("disableEditing")
    }
});
/*添加行*/
function addRow() {
    $('#dg').edatagrid("addRow")
}
/*保存 更新*/
function saveOrUpdateCusDevPlan() {
    $('#dg').edatagrid("saveRow")
}
function loadCusDevPlan() {
    $('#dg').edatagrid("load")
}
/*删除*/
function delCusDevPlan() {
    deleteData('dg',ctx+'/cusDevPlan/deleteCusDevPlan',loadCusDevPlan)
}
/*开发完成 开发失败*/
function updateSaleChanceDevResult(devResult) {
    var id = $('#saleChanceId').val();
    $.ajax({
        url:ctx+"/saleChance/updateSaleChanceDevResult",
        data:{
            id:id ,
            devResult:devResult
        },
        success:function (data) {
            if (data.code===200){
                /*成功*/
                $.messager.alert('来自Crm', data.msg, 'info', function () {
                    // 隐藏工具条
                    $('#toolbar').hide();
                    // 使表格不可编辑
                    $('#dg').edatagrid("disableEditing")
                });
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    })
}