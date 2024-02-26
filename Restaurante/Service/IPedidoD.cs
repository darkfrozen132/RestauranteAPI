using Restaurante.Models;

namespace Restaurante.Service
{
    public interface IPedidoD
    {
        IEnumerable<TaPedidoDetalle> GetPedidoD();

        TaPedidoDetalle ObtenerPorid(int id);

        void add(TaPedidoDetalle taPedidoDetalle);

        void modificar(TaPedidoDetalle taPedidoDetalle);

        void eleminar(int id);

    }
}
