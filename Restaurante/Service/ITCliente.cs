using Restaurante.Models;

namespace Restaurante.Service
{
    public interface ITCliente
    {
        IEnumerable<TaTipoCliente> GetClientesT();

        TaTipoCliente ObtenerPorid(int id);

        void add(TaTipoCliente TaTipoCliente);

        void modificar(TaTipoCliente TaTipoCliente);

        void eleminar(int id);


    }
}
