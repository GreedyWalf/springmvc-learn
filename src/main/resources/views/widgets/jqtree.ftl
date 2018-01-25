<#assign ctx = Request.contextPath/>

<html>
<head>
    <title id='Description'>In this demo the jqxTree is built from JSON data.</title>
    <link rel="stylesheet" href="${ctx}/assets/jqwidgets/styles/jqx.base.css" type="text/css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1"/>
    <script type="text/javascript" src="${ctx}/assets/scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/scripts/demos.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jqwidgets/jqxdata.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jqwidgets/jqxpanel.js"></script>
    <script type="text/javascript" src="${ctx}/assets/jqwidgets/jqxtree.js"></script>
</head>
<body>
<div id='content'>
    <script type="text/javascript">
        $(function () {
            var data = [
                {
                    "id": "1111",
                    "parentid": "",
                    "text": "一级类别",
                    "value": "一级"
                }, {
                    "id": "2222",
                    "parentid": "1111",
                    "text": "二级类别",
                    "value": "二级"
                }, {
                    "id": "3333",
                    "parentid": "2222",
                    "text": "三级类别",
                    "value": "三级"
                }, {
                    "id": "aaaa",
                    "parentid": "",
                    "text": "AAAAA",
                    "value": "一级"
                }, {
                    "id": "bbbb",
                    "text": "BBBBB",
                    "parentid": "aaaa",
                    "value": "二级"
                }];

            var source = {
                datatype: "json",
                datafields: [
                    {name: 'id'},
                    {name: 'parentid'},
                    {name: 'text'},
                    {name: 'value'}
                ],
                id: 'id',
                localdata: data
            };
            var dataAdapter = new $.jqx.dataAdapter(source);
            dataAdapter.dataBind();
            var records = dataAdapter.getRecordsHierarchy('id', 'parentid', 'items', [{name: 'text', map: 'label'}]);
            $('#jqxWidget').jqxTree({source: records, width: '300px'});

            //默认全部展开
            $('#jqxWidget').jqxTree('expandAll');

            //获取所有的节点
            var items = $('#jqxWidget').jqxTree('getItems');
            //获取第一个节点
            items[0].selected = true;
            console.log(items[0]);
            // alert(items[0].id + "--->>" +items[0].label);



            //选中事件
            $('#jqxWidget').on('select',function (event) {
                var args = event.args;
                var item = $('#jqxWidget').jqxTree('getItem', args.element);
                var type = args.type; // mouse, keyboard or null. If the user selects with the mouse, the type will be "mouse".

                var categoryName = item.label;
                var categoryId = item.id;

                alert(categoryId + "--->>" + categoryName);
            });

        });
    </script>
    <div id='jqxWidget'>
    </div>
</div>
</body>

</html>