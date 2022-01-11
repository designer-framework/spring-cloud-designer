<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title></title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        @page {
            size: A4;
        }

        body {
            font-family: 'Microsoft YaHei', serif;
            background: aliceblue;
        }

        .title {
            text-align: center;
            margin-bottom: 10px;
        }

        div > table {
            table-layout: fixed;
            width: 100%;
            margin: 20px 0px;
            text-align: center;
            word-wrap: break-word;
        }

        div table td {
            padding: 5px 0px;
        }

        .div-left {
            float: left;
            width: 49%;
            border: black;
        }

        .div-right {
            float: right;
            width: 49%;
            border: black;
        }
    </style>
</head>
<body>
<div class="title">
    <h2>发票</h2>
    <!-- 头部 start -->
    <div class="div-left">
        <h4>尊敬的用户</h4>
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td>表头1</td>
                <td>表头2</td>
            </tr>
            <tr>
                <td>${curr}</td>
                <td>
                    <table width="80%" border="1" cellspacing="0" cellpadding="0" style="margin: 5px auto;">
                        <tr>
                            <td>${one}</td>
                            <td>${two}</td>
                            <td>${three}</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <!-- 头部 end -->

    <!-- 汇总统计信息 start -->
    <div class="div-right">
        <h4>汇总统计信息</h4>
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td>表头1A</td>
                <td>表头2A</td>
            </tr>
            <tr>
                <td>${curr}</td>
                <td>
                    <table width="80%" border="1" cellspacing="0" cellpadding="0" style="margin: 5px auto;">
                        <tr>
                            <td>${one}</td>
                            <td>${two}</td>
                            <td>${three}</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <!-- 汇总统计信息 end -->

    <!-- 明细 start -->
    <div>
        <h4>明细</h4>
        <table border="1" cellspacing="0" cellpadding="0">
            <tr>
                <td width="5%">序号</td>
                <td width="15%">列1</td>
                <td width="12%">列2</td>
                <td width="12%">列3</td>
                <td width="12%">列4</td>
                <td>列5</td>
            </tr>
            <#list detailList as ad>
                <tr>
                    <td>${ad_index+1}</td>
                    <td>${ad.column1}</td>
                    <td>${ad.column2}</td>
                    <td>${ad.column3}</td>
                    <td>${ad.column4}</td>
                    <td>${ad.column5}</td>
                </tr>
            </#list>
        </table>
    </div>
    <!-- 明细 end -->
</div>
</body>
</html>