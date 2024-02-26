using Restaurante.Models;

namespace Restaurante.Service
{
    public interface IPedidocs
    {
        IEnumerable<TaPedido> GetPedidos();

        TaPedido ObtenerPorid(int id);

        void add(TaPedido taPedido);

        void modificar(TaPedido   taPedido);

        void eleminar(int id);

    }
}
