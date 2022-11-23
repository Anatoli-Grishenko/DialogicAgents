/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogiclient;

/**
 *
 * @author Anatoli Grishenko <Anatoli.Grishenko@gmail.com>
 */
public class AutoSequential extends Interleaved {

//    @Override
//    public String selectWord(String word) {
//        String w;
//        LARVAwait(2000);
//        if (word == null || word.length() == 0) {
//            w = Dict.findFirstWord();
//        } else {
//            w = Dict.findNextWord(word);
//        }
//        Info("Select word " + w);
//        return w;
//    }
//
//    @Override
//    public ArrayList<String> selectReceivers(ArrayList<String> values, boolean multiple) {
//        LARVAwait(2000);
//        ArrayList<String> res = new ArrayList();
//        if (!values.isEmpty()) {
//            Collections.shuffle(values);
//            res.add(values.get(0));
//            if (values.size() > 1 && rollDice(0.5)) {
//                res.add(values.get(1));
//            }
//            if (values.size() > 2 && rollDice(0.5)) {
//                res.add(values.get(2));
//            }
//            if (values.size() > 3 && rollDice(0.5)) {
//                res.add(values.get(3));
//            }
//            if (values.size() > 4 && rollDice(0.5)) {
//                res.add(values.get(3));
//            }
//        }
//        if (res.contains(getLocalName())) {
//            res.remove(getLocalName());
//        }
//        return res;
//    }

}
