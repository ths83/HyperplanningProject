package fr.univtln.tperron710jpoupon997.d12.project;

import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionModel;
import fr.univtln.tperron710jpoupon997.d12.project.connection.CConnectionView;
import fr.univtln.tperron710jpoupon997.d12.project.database.CDataSource;

/**
 * Classe principale
 */
public class CMain
{
    public static void main( String[] args )
    {
        CConnectionModel model = new CConnectionModel();
        CConnectionView view = new CConnectionView(model);
        model.addObserver(view);
    }
}
