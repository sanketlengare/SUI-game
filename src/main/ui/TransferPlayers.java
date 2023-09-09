package ui;

import model.Event;
import model.EventLog;
import ui.Game;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

// represents a transfer handler for drag and drop
public class TransferPlayers extends TransferHandler {

    // EFFECTS : move operation
    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    // EFFECTS : prepares data to be transferable
    @Override
    protected Transferable createTransferable(JComponent c) {
        JList dragSrc = (JList) c;
        List<String> values = dragSrc.getSelectedValuesList();
        String data = "";
        for (String str : values) {
            data = data + str + "\n";
        }
        data.trim();
        return (new StringSelection(data));
    }

    @Override
    // EFFECTS : checks if transfer data is of string flavor, if so then returns true
    public boolean canImport(TransferSupport support) {
        if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS : imports data after drag action performed
    public boolean importData(TransferHandler.TransferSupport info) {
        String data = null;
        if (!canImport(info)) {
            return false;
        }
        JList list = (JList) info.getComponent();
        DefaultListModel model = (DefaultListModel) list.getModel();
        data = getString(info);
        if (data == null) {
            return false;
        }
        if (info.isDrop()) { //This is a drop
            JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
            int index = dl.getIndex();
            if (dl.isInsert()) {
                return extracted1(data, model, index);
            } else if (extracted(data, model, index)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES : model
    // EFFECTS : if data does not contain Empty Position then add to model
    private boolean extracted1(String data, DefaultListModel model, int index) {
        if (!data.contains("Empty Position")) {
            model.add(index, data);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS : returns the string that data holds
    private String getString(TransferSupport info) {
        String data;
        try {
            data = (String) info.getTransferable().getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException ufe) {
            System.out.println("importData: unsupported data flavor");
            return null;
        } catch (IOException ioe) {
            System.out.println("importData: I/O exception");
            return null;
        }
        return data;
    }

    // MODIFIES : model
    // EFFECTS : if data does not contain Empty Position then set player model index and add it back
    private boolean extracted(String data, DefaultListModel model, int index) {
        if (!data.contains("Empty Position")) {
            DefaultListModel listModel = (DefaultListModel) Game.getDragLoc().getModel();
            DefaultListModel listModel2 = (DefaultListModel) Game.getDragSrc().getModel();
            if (!listModel.get(index).equals("Empty Position")) {
                listModel2.addElement(listModel.get(index));
                model.set(index, data);
                Game.replacePlayer(data.trim(), listModel2.get(listModel2.size() - 1).toString().trim());

            } else if (listModel.get(index).equals("Empty Position")) {
                model.set(index, data);
                Game.addPlayer(data.substring(0, data.length() - 1), index);

            }
            return true;
        }
        return false;
    }


    // MODIFIES : source list
    // EFFECTS : clears items from source when move operation is performed
    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        if (action == TransferHandler.MOVE) {
            JList srcList = (JList) source;
            int index = srcList.getSelectedIndex();
            DefaultListModel srcDLModel = (DefaultListModel) srcList.getModel();
            srcDLModel.removeElementAt(index);
        }
    }
}

