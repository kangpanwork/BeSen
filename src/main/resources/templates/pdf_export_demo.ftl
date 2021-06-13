<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Title</title>
    <style>
        @page {
            size: 100mm 100mm;
        }

        table{background: #ccc;margin: 10px;border-collapse: collapse; font-family: SimSun;}

        td {height:25px;line-height:25px;text-align:center;border:1px solid #ccc;}

        tr{background: #fff;}

    </style>

</head>
<body>

<table class="table" border="1">
    <tr class="title">
        <td>
            学号
        </td>
        <td>
            姓名
        </td>
        <td>
            性别
        </td>
        <td>
            年龄
        </td>
        <td>
            班级
        </td>
    </tr>
    <#list students as student>
        <tr>
            <td>
                <#if student.stuNo??>
                    ${student.stuNo}
                </#if>
            </td>
            <td>
                <#if student.stuName??>
                    ${student.stuName}
                </#if>
            </td>
            <td>
                <#if student.stuSex??>
                    ${student.stuSex}
                </#if>
            </td>
            <td>
                <#if student.stuAge??>
                    ${student.stuAge}
                </#if>
            </td>
            <td>
                <#if student.stuClass??>
                    ${student.stuClass}
                </#if>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>
