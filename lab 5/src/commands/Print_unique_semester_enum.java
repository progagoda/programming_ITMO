package commands;

import helpers.Messages;

import java.util.Scanner;

//public class Print_unique_semester_enum  extends Command{
//    @Override
//    public void printInfoAboutCommand() {
//        System.out.println("print_semesters : вывести уникальные значения поля semesterEnum всех элементов в коллекции)");
//    }
//
//    @Override
//    public void execute(String[] args) {
//        try {
//            if (args.length == 1) {
//                String admin = args[1];
//                if (!receiver.printFieldDescendingSemester(admin)) {
//                    Messages.normalMessageOutput("В коллекции нет эллементов, так что вывод пуст");
//                }
//            } else {
//                Messages.normalMessageOutput("Неправильный ввод агрументов, попробуйте еще раз");
//            }
//        }
//        catch (ArrayIndexOutOfBoundsException e){
//            Messages.normalMessageOutput("Нужно ввести аргумент - имя админа группы ");
//        }
//    }
//
//    @Override
//    public void execute(String[] args, Scanner scanner) {
//
//    }
//}
