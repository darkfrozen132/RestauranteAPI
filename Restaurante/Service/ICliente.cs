using Restaurante.Models;

namespace Restaurante.Service
{
    public interface ICliente
    {

        IEnumerable<TaCliente> GetClientes();

        TaCliente ObtenerPorid(int id);

        void add(TaCliente taCliente);

        void modificar(TaCliente taCliente);

        void eleminar(int id);

    }
}
