## 一、安装git--参照网上教程即可安装
## 二、基本使用流程（以D:\GitRepository\studynotes目录作为根目录）
1. 初始化`本地repository`,在当前目录生成一个.git目录（隐藏的文件夹）

    ```
    git init
    ```
2. 创建一个文件 readme.txt ,并写入 hello，world。然后使用命令将文件添加至暂存区：
    ```
    git add readme.txt           --添加单个文件(当前使用)
    git add readme.txt readme1.txt readme2.txt  --添加多个文件
    git add FrontEnd/     --添加整个目录里的文件
    ```
3. 将在暂存区的readme.txt文件提交到master
    ```
    git commit -m "my first commit"
    ```
4. 将本地的repository与github上的repository关联起来使用：
    * 在github上新建一个studynotes的repository
    * 使用命令 ```git remote add origin git@github.com:yangminWFN/studynotes.git ```来与远程库关联
    * 使用命令 ``` git pull --rebase origin master ```来确保本地仓库具有远程库的 readme.md 文件（如果有的话）
    * 使用命令 ``` git push -u origin master ``` 来将本地库里的文件上传到远程库中
    * 至此，本地库与远程库保持一致了
## 三、常用命令
1. 提交添加文件到暂存区
    ```
    git add filename
    ```
2. 提交删除文件到暂存区
    ```
    git rm filename
    ```
3. 提交暂存区文件到repository
    ```
    git commit -m "*****"
    ```
4. 显示所有commit记录
    ```
    git log
    ```
5. 回退到特定版本
    ```
    git reset --hard commitID(如36cc37a905cfa4b9243de54db686288d32095f01)
    ```
6. 查看历史命令
    ```
    git reflog
    ```
7. 查看当前git状态
    ```
    git status
    ```
8. 使用repository中的文件来替换本地工作区的文件
    ```
    git checkout -- readme.txt
    ```
9. 撤销暂存区中的修改文件（git add 后）
    ```
    git reset HEAD readme.txt
    ```
10. clone 远程repository
    ```
    git clone git@github.com:yangminWFN/gitskills.git
    ```